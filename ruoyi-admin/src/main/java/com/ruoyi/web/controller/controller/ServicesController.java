package com.ruoyi.web.controller.controller;

import com.ruoyi.cmfg.domain.Services;
import com.ruoyi.cmfg.service.IServicesService;
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
 * 服务Controller
 * 
 * @author ruoyi
 * @date 2024-01-24
 */
@RestController
@RequestMapping("/cmfg/ser_manage")
public class ServicesController extends BaseController
{
    @Autowired
    private IServicesService servicesService;

    /**
     * 查询服务列表
     */
    @PreAuthorize("@ss.hasPermi('cmfg:ser_manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(Services services)
    {
        startPage();
        List<Services> list = servicesService.selectServicesList(services);
        return getDataTable(list);
    }

    /**
     * 导出服务列表
     */
    @PreAuthorize("@ss.hasPermi('cmfg:ser_manage:export')")
    @Log(title = "服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Services services)
    {
        List<Services> list = servicesService.selectServicesList(services);
        ExcelUtil<Services> util = new ExcelUtil<Services>(Services.class);
        util.exportExcel(response, list, "服务数据");
    }

    /**
     * 获取服务详细信息
     */
    @PreAuthorize("@ss.hasPermi('cmfg:ser_manage:query')")
    @GetMapping(value = "/{serId}")
    public AjaxResult getInfo(@PathVariable("serId") Long serId)
    {
        return success(servicesService.selectServicesBySerId(serId));
    }

    /**
     * 新增服务
     */
    @PreAuthorize("@ss.hasPermi('cmfg:ser_manage:add')")
    @Log(title = "服务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Services services)
    {
        return toAjax(servicesService.insertServices(services));
    }

    /**
     * 修改服务
     */
    @PreAuthorize("@ss.hasPermi('cmfg:ser_manage:edit')")
    @Log(title = "服务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Services services)
    {
        return toAjax(servicesService.updateServices(services));
    }

    /**
     * 删除服务
     */
    @PreAuthorize("@ss.hasPermi('cmfg:ser_manage:remove')")
    @Log(title = "服务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serIds}")
    public AjaxResult remove(@PathVariable Long[] serIds)
    {
        return toAjax(servicesService.deleteServicesBySerIds(serIds));
    }
}
