package com.ruoyi.cmfg.mapper;

import java.util.List;
import com.ruoyi.cmfg.domain.Project;
import com.ruoyi.cmfg.domain.Task;

/**
 * 项目管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-27
 */
public interface ProjectMapper 
{
    /**
     * 查询项目管理
     * 
     * @param projId 项目管理主键
     * @return 项目管理
     */
    public Project selectProjectByProjId(Long projId);

    /**
     * 查询项目管理列表
     * 
     * @param project 项目管理
     * @return 项目管理集合
     */
    public List<Project> selectProjectList(Project project);

    /**
     * 新增项目管理
     * 
     * @param project 项目管理
     * @return 结果
     */
    public int insertProject(Project project);

    /**
     * 修改项目管理
     * 
     * @param project 项目管理
     * @return 结果
     */
    public int updateProject(Project project);

    /**
     * 删除项目管理
     * 
     * @param projId 项目管理主键
     * @return 结果
     */
    public int deleteProjectByProjId(Long projId);

    /**
     * 批量删除项目管理
     * 
     * @param projIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProjectByProjIds(Long[] projIds);

    /**
     * 批量删除任务管理
     * 
     * @param projIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskByProjIds(Long[] projIds);
    
    /**
     * 批量新增任务管理
     * 
     * @param taskList 任务管理列表
     * @return 结果
     */
    public int batchTask(List<Task> taskList);
    

    /**
     * 通过项目管理主键删除任务管理信息
     * 
     * @param projId 项目管理ID
     * @return 结果
     */
    public int deleteTaskByProjId(Long projId);
}
