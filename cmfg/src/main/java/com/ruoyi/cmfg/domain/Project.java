package com.ruoyi.cmfg.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目管理对象 project
 * 
 * @author ruoyi
 * @date 2024-02-18
 */
public class Project extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目ID */
    private Long projId;

    /** 工期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "工期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rlsTime;

    /** 数量 */
    @Excel(name = "数量")
    private Long numT;

    /** 需求方ID */
    @Excel(name = "需求方ID")
    private Long userId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer finish;

    public void setProjId(Long projId) 
    {
        this.projId = projId;
    }

    public Long getProjId() 
    {
        return projId;
    }
    public void setRlsTime(Date rlsTime) 
    {
        this.rlsTime = rlsTime;
    }

    public Date getRlsTime() 
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
    public void setFinish(Integer finish) 
    {
        this.finish = finish;
    }

    public Integer getFinish() 
    {
        return finish;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projId", getProjId())
            .append("rlsTime", getRlsTime())
            .append("numT", getNumT())
            .append("userId", getUserId())
            .append("finish", getFinish())
            .toString();
    }
}
