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
public class Resource extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 资源ID
     */
    private Long resId;

    /**
     * 所需服务类型
     */
    @Excel(name = "所需服务类型")
    private String serType;

    /**
     * 制造资源名称
     */
    @Excel(name = "制造资源名称")
    private String resName;

    /**
     * 可用容量上限
     */
    @Excel(name = "可用容量上限")
    private Long maxVol;

    /**
     * 服务单价
     */
    @Excel(name = "服务单价")
    private Long cost;

    /**
     * 服务质量
     */
    @Excel(name = "服务质量")
    private Long quality;

    /**
     * 提供商ID
     */
    @Excel(name = "提供商ID")
    private Long userId;

    /**
     * 所属服务ID
     */
    @Excel(name = "所属服务ID")
    private Long serId;

    /**
     * 注册时间
     */
    @Excel(name = "注册时间")
    private String dateTime;

    /**
     * 制造资源选配方式
     */
    @Excel(name = "制造资源选配方式")
    private String selType;

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public Long getResId() {
        return resId;
    }

    public void setSerType(String serType) {
        this.serType = serType;
    }

    public String getSerType() {
        return serType;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResName() {
        return resName;
    }

    public void setMaxVol(Long maxVol) {
        this.maxVol = maxVol;
    }

    public Long getMaxVol() {
        return maxVol;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getCost() {
        return cost;
    }

    public void setQuality(Long quality) {
        this.quality = quality;
    }

    public Long getQuality() {
        return quality;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setSerId(Long serId) {
        this.serId = serId;
    }

    public Long getSerId() {
        return serId;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setSelType(String selType) {
        this.selType = selType;
    }

    public String getSelType() {
        return selType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("resId", getResId())
                .append("serType", getSerType())
                .append("resName",getResName())
                .append("maxVol", getMaxVol())
                .append("cost", getCost())
                .append("quality", getQuality())
                .append("userId", getUserId())
                .append("serId", getSerId())
                .append("dateTime",getDateTime())
                .append("selType", getSelType())
                .toString();
    }
}
