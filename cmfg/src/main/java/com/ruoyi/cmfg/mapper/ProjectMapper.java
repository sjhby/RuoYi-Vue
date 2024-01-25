package com.ruoyi.cmfg.mapper;

import java.util.List;
import com.ruoyi.cmfg.domain.Project;

/**
 * 项目管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-24
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
}
