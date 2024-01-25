package com.ruoyi.cmfg.service;

import java.util.List;
import com.ruoyi.cmfg.domain.Task;

/**
 * 任务管理Service接口
 * 
 * @author ruoyi
 * @date 2024-01-24
 */
public interface ITaskService 
{
    /**
     * 查询任务管理
     * 
     * @param taskId 任务管理主键
     * @return 任务管理
     */
    public Task selectTaskByTaskId(Long taskId);

    /**
     * 查询任务管理列表
     * 
     * @param task 任务管理
     * @return 任务管理集合
     */
    public List<Task> selectTaskList(Task task);

    /**
     * 新增任务管理
     * 
     * @param task 任务管理
     * @return 结果
     */
    public int insertTask(Task task);

    /**
     * 修改任务管理
     * 
     * @param task 任务管理
     * @return 结果
     */
    public int updateTask(Task task);

    /**
     * 批量删除任务管理
     * 
     * @param taskIds 需要删除的任务管理主键集合
     * @return 结果
     */
    public int deleteTaskByTaskIds(Long[] taskIds);

    /**
     * 删除任务管理信息
     * 
     * @param taskId 任务管理主键
     * @return 结果
     */
    public int deleteTaskByTaskId(Long taskId);
}
