package com.ruoyi.cmfg.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cmfg.mapper.ResourceMapper;
import com.ruoyi.cmfg.domain.Resource;
import com.ruoyi.cmfg.service.IResourceService;

/**
 * 资源Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-24
 */
@Service
public class ResourceServiceImpl implements IResourceService 
{
    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 查询资源
     * 
     * @param resId 资源主键
     * @return 资源
     */
    @Override
    public Resource selectResourceByResId(Long resId)
    {
        return resourceMapper.selectResourceByResId(resId);
    }

    /**
     * 查询资源列表
     * 
     * @param resource 资源
     * @return 资源
     */
    @Override
    public List<Resource> selectResourceList(Resource resource)
    {
        return resourceMapper.selectResourceList(resource);
    }

    /**
     * !!!!标记
     * 查询资源列表
     *
     * @param resource 资源
     * @return 资源
     */
    @Override
    public List<Resource> selectResourceListByUserId(Long userId)
    {
        return resourceMapper.selectResourceByUserId(userId);
    }

    /**
     * 新增资源
     * 
     * @param resource 资源
     * @return 结果
     */
    @Override
    public int insertResource(Resource resource)
    {
        return resourceMapper.insertResource(resource);
    }

    /**
     * 修改资源
     * 
     * @param resource 资源
     * @return 结果
     */
    @Override
    public int updateResource(Resource resource)
    {
        return resourceMapper.updateResource(resource);
    }

    /**
     * 批量删除资源
     * 
     * @param resIds 需要删除的资源主键
     * @return 结果
     */
    @Override
    public int deleteResourceByResIds(Long[] resIds)
    {
        return resourceMapper.deleteResourceByResIds(resIds);
    }

    /**
     * 删除资源信息
     * 
     * @param resId 资源主键
     * @return 结果
     */
    @Override
    public int deleteResourceByResId(Long resId)
    {
        return resourceMapper.deleteResourceByResId(resId);
    }
}
