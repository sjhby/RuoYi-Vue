package com.ruoyi.cmfg.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cmfg.mapper.ServicesMapper;
import com.ruoyi.cmfg.domain.Services;
import com.ruoyi.cmfg.service.IServicesService;

/**
 * 服务Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-24
 */
@Service
public class ServicesServiceImpl implements IServicesService 
{
    @Autowired
    private ServicesMapper servicesMapper;

    /**
     * 查询服务
     * 
     * @param serId 服务主键
     * @return 服务
     */
    @Override
    public Services selectServicesBySerId(Long serId)
    {
        return servicesMapper.selectServicesBySerId(serId);
    }

    /**
     * 查询服务列表
     * 
     * @param services 服务
     * @return 服务
     */
    @Override
    public List<Services> selectServicesList(Services services)
    {
        return servicesMapper.selectServicesList(services);
    }

    /**
     * 新增服务
     * 
     * @param services 服务
     * @return 结果
     */
    @Override
    public int insertServices(Services services)
    {
        return servicesMapper.insertServices(services);
    }

    /**
     * 修改服务
     * 
     * @param services 服务
     * @return 结果
     */
    @Override
    public int updateServices(Services services)
    {
        return servicesMapper.updateServices(services);
    }

    /**
     * 批量删除服务
     * 
     * @param serIds 需要删除的服务主键
     * @return 结果
     */
    @Override
    public int deleteServicesBySerIds(Long[] serIds)
    {
        return servicesMapper.deleteServicesBySerIds(serIds);
    }

    /**
     * 删除服务信息
     * 
     * @param serId 服务主键
     * @return 结果
     */
    @Override
    public int deleteServicesBySerId(Long serId)
    {
        return servicesMapper.deleteServicesBySerId(serId);
    }
}
