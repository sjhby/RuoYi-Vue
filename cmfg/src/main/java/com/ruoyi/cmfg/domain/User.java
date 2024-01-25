package com.ruoyi.cmfg.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户管理对象 user
 * 
 * @author ruoyi
 * @date 2024-01-24
 */
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户Id */
    private Long userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 用户密码 */
    @Excel(name = "用户密码")
    private String userPasswd;

    /** 位置 */
    @Excel(name = "位置")
    private String loc;

    /** 用户类型 */
    @Excel(name = "用户类型")
    private String userType;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setUserPasswd(String userPasswd) 
    {
        this.userPasswd = userPasswd;
    }

    public String getUserPasswd() 
    {
        return userPasswd;
    }
    public void setLoc(String loc) 
    {
        this.loc = loc;
    }

    public String getLoc() 
    {
        return loc;
    }
    public void setUserType(String userType) 
    {
        this.userType = userType;
    }

    public String getUserType() 
    {
        return userType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("userPasswd", getUserPasswd())
            .append("loc", getLoc())
            .append("userType", getUserType())
            .toString();
    }
}
