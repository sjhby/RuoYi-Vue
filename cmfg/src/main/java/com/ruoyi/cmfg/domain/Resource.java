package com.ruoyi.cmfg.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资源对象 resource
 * 
 * @author ruoyi
 * @date 2024-01-24
 */
public class Resource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资源ID */
    private Long resId;

    /** 所需服务类型 */
    @Excel(name = "所需服务类型")
    private String serType;

    /** 最大数量 */
    @Excel(name = "最大数量")
    private Long maxVol;

    /** 成本 */
    @Excel(name = "成本")
    private Long cost;

    /** 质量 */
    @Excel(name = "质量")
    private Long quality;

    /** 提供商ID */
    @Excel(name = "提供商ID")
    private Long userId;

    /** 所属服务ID */
    @Excel(name = "所属服务ID")
    private Long serId;

    public void setResId(Long resId) 
    {
        this.resId = resId;
    }

    public Long getResId() 
    {
        return resId;
    }
    public void setSerType(String serType) 
    {
        this.serType = serType;
    }

    public String getSerType() 
    {
        return serType;
    }
    public void setMaxVol(Long maxVol) 
    {
        this.maxVol = maxVol;
    }

    public Long getMaxVol() 
    {
        return maxVol;
    }
    public void setCost(Long cost) 
    {
        this.cost = cost;
    }

    public Long getCost() 
    {
        return cost;
    }
    public void setQuality(Long quality) 
    {
        this.quality = quality;
    }

    public Long getQuality() 
    {
        return quality;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setSerId(Long serId) 
    {
        this.serId = serId;
    }

    public Long getSerId() 
    {
        return serId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("resId", getResId())
            .append("serType", getSerType())
            .append("maxVol", getMaxVol())
            .append("cost", getCost())
            .append("quality", getQuality())
            .append("userId", getUserId())
            .append("serId", getSerId())
            .toString();
    }
}
