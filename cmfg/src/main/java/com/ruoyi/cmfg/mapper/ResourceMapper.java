package com.ruoyi.cmfg.mapper;

import java.util.List;
import com.ruoyi.cmfg.domain.Resource;

/**
 * 资源Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-24
 */
public interface ResourceMapper 
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
     * 删除资源
     * 
     * @param resId 资源主键
     * @return 结果
     */
    public int deleteResourceByResId(Long resId);

    /**
     * 批量删除资源
     * 
     * @param resIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteResourceByResIds(Long[] resIds);
}
