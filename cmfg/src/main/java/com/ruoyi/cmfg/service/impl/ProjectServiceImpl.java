package com.ruoyi.cmfg.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.cmfg.domain.Task;
import com.ruoyi.cmfg.mapper.ProjectMapper;
import com.ruoyi.cmfg.domain.Project;
import com.ruoyi.cmfg.service.IProjectService;

/**
 * 项目管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-27
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
    @Transactional
    @Override
    public int insertProject(Project project)
    {
        int rows = projectMapper.insertProject(project);
        insertTask(project);
        return rows;
    }

    /**
     * 修改项目管理
     * 
     * @param project 项目管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateProject(Project project)
    {
        projectMapper.deleteTaskByProjId(project.getProjId());
        insertTask(project);
        return projectMapper.updateProject(project);
    }

    /**
     * 批量删除项目管理
     * 
     * @param projIds 需要删除的项目管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteProjectByProjIds(Long[] projIds)
    {
        projectMapper.deleteTaskByProjIds(projIds);
        return projectMapper.deleteProjectByProjIds(projIds);
    }

    /**
     * 删除项目管理信息
     * 
     * @param projId 项目管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteProjectByProjId(Long projId)
    {
        projectMapper.deleteTaskByProjId(projId);
        return projectMapper.deleteProjectByProjId(projId);
    }

    /**
     * 新增任务管理信息
     * 
     * @param project 项目管理对象
     */
    public void insertTask(Project project)
    {
        List<Task> taskList = project.getTaskList();
        Long projId = project.getProjId();
        if (StringUtils.isNotNull(taskList))
        {
            List<Task> list = new ArrayList<Task>();
            for (Task task : taskList)
            {
                task.setProjId(projId);
                list.add(task);
            }
            if (list.size() > 0)
            {
                projectMapper.batchTask(list);
            }
        }
    }
}
