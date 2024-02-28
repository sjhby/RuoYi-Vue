package com.ruoyi.cmfg.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务管理对象 task
 * 
 * @author ruoyi
 * @date 2024-02-27
 */
public class Task extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long taskId;

    /** 服务类型 */
    @Excel(name = "服务类型")
    private String serType;

    /** 工期 */
    @Excel(name = "工期")
    private Long proceTime;

    /** 资源数量 */
    @Excel(name = "资源数量")
    private Long proceVol;

    /** 是否完成 */
    @Excel(name = "是否完成")
    private String succs;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long projId;

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setSerType(String serType) 
    {
        this.serType = serType;
    }

    public String getSerType() 
    {
        return serType;
    }
    public void setProceTime(Long proceTime) 
    {
        this.proceTime = proceTime;
    }

    public Long getProceTime() 
    {
        return proceTime;
    }
    public void setProceVol(Long proceVol) 
    {
        this.proceVol = proceVol;
    }

    public Long getProceVol() 
    {
        return proceVol;
    }
    public void setSuccs(String succs) 
    {
        this.succs = succs;
    }

    public String getSuccs() 
    {
        return succs;
    }
    public void setProjId(Long projId) 
    {
        this.projId = projId;
    }

    public Long getProjId() 
    {
        return projId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskId", getTaskId())
            .append("serType", getSerType())
            .append("proceTime", getProceTime())
            .append("proceVol", getProceVol())
            .append("succs", getSuccs())
            .append("projId", getProjId())
            .toString();
    }
}
