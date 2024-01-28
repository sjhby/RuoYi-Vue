package com.ruoyi.web.controller.cmfg;

import com.ruoyi.cmfg.domain.Project;
import com.ruoyi.cmfg.service.IProjectService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 项目管理Controller
 * 
 * @author ruoyi
 * @date 2024-01-24
 */
@RestController
@RequestMapping("/cmfg/project_manage")
public class ProjectController extends BaseController
{
    @Autowired
    private IProjectService projectService;
    @GetMapping("/test")
    public String Test(){
        return "hello world";
    }

    /**
     * 查询项目管理列表
     */
    @PreAuthorize("@ss.hasPermi('cmfg:project_manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(Project project)
    {
        startPage();
        List<Project> list = projectService.selectProjectList(project);
        return getDataTable(list);
    }


    /**
     * 导出项目管理列表
     */
    @PreAuthorize("@ss.hasPermi('cmfg:project_manage:export')")
    @Log(title = "项目管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Project project)
    {
        List<Project> list = projectService.selectProjectList(project);
        ExcelUtil<Project> util = new ExcelUtil<Project>(Project.class);
        util.exportExcel(response, list, "项目管理数据");
    }

    /**
     * 获取项目管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('cmfg:project_manage:query')")
    @GetMapping(value = "/{projId}")
    public AjaxResult getInfo(@PathVariable("projId") Long projId)
    {
        return success(projectService.selectProjectByProjId(projId));
    }

    /**
     * 新增项目管理
     */
    @PreAuthorize("@ss.hasPermi('cmfg:project_manage:add')")
    @Log(title = "项目管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Project project)
    {
        return toAjax(projectService.insertProject(project));
    }

    /**
     * 修改项目管理
     */
    @PreAuthorize("@ss.hasPermi('cmfg:project_manage:edit')")
    @Log(title = "项目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Project project)
    {
        return toAjax(projectService.updateProject(project));
    }

    /**
     * 删除项目管理
     */
    @PreAuthorize("@ss.hasPermi('cmfg:project_manage:remove')")
    @Log(title = "项目管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projIds}")
    public AjaxResult remove(@PathVariable Long[] projIds)
    {
        return toAjax(projectService.deleteProjectByProjIds(projIds));
    }
}
