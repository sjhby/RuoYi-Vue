package com.ruoyi.web.controller.controller;

import com.ruoyi.cmfg.domain.Resource;
import com.ruoyi.cmfg.service.IResourceService;
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
 * 资源Controller
 * 
 * @author ruoyi
 * @date 2024-01-24
 */
@RestController
@RequestMapping("/cmfg/res_manage")
public class ResourceController extends BaseController
{
    @Autowired
    private IResourceService resourceService;

    /**
     * 查询资源列表
     */
    @PreAuthorize("@ss.hasPermi('cmfg:res_manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(Resource resource)
    {
        startPage();
        List<Resource> list = resourceService.selectResourceList(resource);
        return getDataTable(list);
    }

    /**
     * 导出资源列表
     */
    @PreAuthorize("@ss.hasPermi('cmfg:res_manage:export')")
    @Log(title = "资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Resource resource)
    {
        List<Resource> list = resourceService.selectResourceList(resource);
        ExcelUtil<Resource> util = new ExcelUtil<Resource>(Resource.class);
        util.exportExcel(response, list, "资源数据");
    }

    /**
     * 获取资源详细信息
     */
    @PreAuthorize("@ss.hasPermi('cmfg:res_manage:query')")
    @GetMapping(value = "/{resId}")
    public AjaxResult getInfo(@PathVariable("resId") Long resId)
    {
        return success(resourceService.selectResourceByResId(resId));
    }

    /**
     * 新增资源
     */
    @PreAuthorize("@ss.hasPermi('cmfg:res_manage:add')")
    @Log(title = "资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Resource resource)
    {
        return toAjax(resourceService.insertResource(resource));
    }

    /**
     * 修改资源
     */
    @PreAuthorize("@ss.hasPermi('cmfg:res_manage:edit')")
    @Log(title = "资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Resource resource)
    {
        return toAjax(resourceService.updateResource(resource));
    }

    /**
     * 删除资源
     */
    @PreAuthorize("@ss.hasPermi('cmfg:res_manage:remove')")
    @Log(title = "资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{resIds}")
    public AjaxResult remove(@PathVariable Long[] resIds)
    {
        return toAjax(resourceService.deleteResourceByResIds(resIds));
    }
}
