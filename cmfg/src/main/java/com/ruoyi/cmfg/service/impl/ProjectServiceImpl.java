package com.ruoyi.cmfg.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cmfg.mapper.ProjectMapper;
import com.ruoyi.cmfg.domain.Project;
import com.ruoyi.cmfg.service.IProjectService;

/**
 * 项目管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-24
 */
@Service
public class ProjectServiceImpl implements IProjectService 
{
    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 查询项目管理
     * 
     * @param projId 项目管理主键
     * @return 项目管理
     */
    @Override
    public Project selectProjectByProjId(Long projId)
    {
        return projectMapper.selectProjectByProjId(projId);
    }

    /**
     * 查询项目管理列表
     * 
     * @param project 项目管理
     * @return 项目管理
     */
    @Override
    public List<Project> selectProjectList(Project project)
    {
        return projectMapper.selectProjectList(project);
    }

    /**
     * 新增项目管理
     * 
     * @param project 项目管理
     * @return 结果
     */
    @Override
    public int insertProject(Project project)
    {
        return projectMapper.insertProject(project);
    }

    /**
     * 修改项目管理
     * 
     * @param project 项目管理
     * @return 结果
     */
    @Override
    public int updateProject(Project project)
    {
        return projectMapper.updateProject(project);
    }

    /**
     * 批量删除项目管理
     * 
     * @param projIds 需要删除的项目管理主键
     * @return 结果
     */
    @Override
    public int deleteProjectByProjIds(Long[] projIds)
    {
        return projectMapper.deleteProjectByProjIds(projIds);
    }

    /**
     * 删除项目管理信息
     * 
     * @param projId 项目管理主键
     * @return 结果
     */
    @Override
    public int deleteProjectByProjId(Long projId)
    {
        return projectMapper.deleteProjectByProjId(projId);
    }
}
