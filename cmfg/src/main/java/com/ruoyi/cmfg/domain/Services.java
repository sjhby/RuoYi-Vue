package com.ruoyi.cmfg.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务对象 services
 * 
 * @author ruoyi
 * @date 2024-01-24
 */
public class Services extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务ID */
    private Long serId;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 系数 */
    @Excel(name = "系数")
    private Long delta;

    /** 最大数量 */
    @Excel(name = "最大数量")
    private Long maxQlen;

    public void setSerId(Long serId) 
    {
        this.serId = serId;
    }

    public Long getSerId() 
    {
        return serId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setDelta(Long delta) 
    {
        this.delta = delta;
    }

    public Long getDelta() 
    {
        return delta;
    }
    public void setMaxQlen(Long maxQlen) 
    {
        this.maxQlen = maxQlen;
    }

    public Long getMaxQlen() 
    {
        return maxQlen;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serId", getSerId())
            .append("type", getType())
            .append("delta", getDelta())
            .append("maxQlen", getMaxQlen())
            .toString();
    }
}
