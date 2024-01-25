package com.ruoyi.cmfg.mapper;

import java.util.List;
import com.ruoyi.cmfg.domain.Services;

/**
 * 服务Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-24
 */
public interface ServicesMapper 
{
    /**
     * 查询服务
     * 
     * @param serId 服务主键
     * @return 服务
     */
    public Services selectServicesBySerId(Long serId);

    /**
     * 查询服务列表
     * 
     * @param services 服务
     * @return 服务集合
     */
    public List<Services> selectServicesList(Services services);

    /**
     * 新增服务
     * 
     * @param services 服务
     * @return 结果
     */
    public int insertServices(Services services);

    /**
     * 修改服务
     * 
     * @param services 服务
     * @return 结果
     */
    public int updateServices(Services services);

    /**
     * 删除服务
     * 
     * @param serId 服务主键
     * @return 结果
     */
    public int deleteServicesBySerId(Long serId);

    /**
     * 批量删除服务
     * 
     * @param serIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteServicesBySerIds(Long[] serIds);
}
