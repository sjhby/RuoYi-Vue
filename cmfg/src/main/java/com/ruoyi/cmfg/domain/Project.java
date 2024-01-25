package com.ruoyi.cmfg.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目管理对象 project
 * 
 * @author ruoyi
 * @date 2024-01-24
 */
public class Project extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目ID */
    private Long projId;

    /** 工期 */
    @Excel(name = "工期")
    private Long rlsTime;

    /** 数量 */
    @Excel(name = "数量")
    private Long numT;

    /** 需求方ID */
    @Excel(name = "需求方ID")
    private Long userId;

    public void setProjId(Long projId) 
    {
        this.projId = projId;
    }

    public Long getProjId() 
    {
        return projId;
    }
    public void setRlsTime(Long rlsTime) 
    {
        this.rlsTime = rlsTime;
    }

    public Long getRlsTime() 
    {
        return rlsTime;
    }
    public void setNumT(Long numT) 
    {
        this.numT = numT;
    }

    public Long getNumT() 
    {
        return numT;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projId", getProjId())
            .append("rlsTime", getRlsTime())
            .append("numT", getNumT())
            .append("userId", getUserId())
            .toString();
    }
}
