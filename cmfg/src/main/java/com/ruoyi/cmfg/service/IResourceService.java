package com.ruoyi.cmfg.service;

import java.util.List;
import com.ruoyi.cmfg.domain.Resource;

/**
 * 资源Service接口
 * 
 * @author ruoyi
 * @date 2024-01-24
 */
public interface IResourceService 
{
    /**
     * 查询资源
     * 
     * @param resId 资源主键
     * @return 资源
     */
    public Resource selectResourceByResId(Long resId);

    /**
     * 查询资源列表
     * 
     * @param resource 资源
     * @return 资源集合
     */
    public List<Resource> selectResourceList(Resource resource);

    /**
     * !!!!标记
     * 查询资源列表
     *
     * @param resource 资源
     * @return 资源集合
     */
    public List<Resource> selectResourceListByUserId(Long userId);

    /**
     * 新增资源
     * 
     * @param resource 资源
     * @return 结果
     */
    public int insertResource(Resource resource);

    /**
     * 修改资源
     * 
     * @param resource 资源
     * @return 结果
     */
    public int updateResource(Resource resource);

    /**
     * 批量删除资源
     * 
     * @param resIds 需要删除的资源主键集合
     * @return 结果
     */
    public int deleteResourceByResIds(Long[] resIds);

    /**
     * 删除资源信息
     * 
     * @param resId 资源主键
     * @return 结果
     */
    public int deleteResourceByResId(Long resId);
}
