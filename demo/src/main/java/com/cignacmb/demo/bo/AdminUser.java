package com.cignacmb.demo.bo;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.cignacmb.demo.utils.Util;

/**
 * 
 * @类名： AdminUser.java 
 * @描述：系统用户对象
 * @作者： mxyanx
 * @修改日期： 2014年11月14日
 */
@Component
public class AdminUser
{
    private Long id;

    private String userName;

    private String password;

    private String createTimeDesc;

    private Date createTime;

    private Date updateTime;

    private String updateTimeDesc;

    private String role;


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public String getUserName()
    {
        return userName;
    }


    public void setUserName(String userName)
    {
        this.userName = userName == null ? null : userName.trim();
    }


    public String getPassword()
    {
        return password;
    }


    public void setPassword(String password)
    {
        this.password = password == null ? null : password.trim();
    }


    public Date getCreateTime()
    {
        return createTime;
    }


    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }


    public Date getUpdateTime()
    {
        return updateTime;
    }


    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }


    public String getRole()
    {
        return role;
    }


    public void setRole(String role)
    {
        this.role = role == null ? null : role.trim();
    }


    public String getCreateTimeDesc()
    {
        if (this.createTime != null)
        {
            return Util.getTimeByDate(this.createTime);
        }
        else
        {
            return null;
        }
    }


    public void setCreateTimeDesc(String createTimeDesc)
    {
        this.createTimeDesc = createTimeDesc;
    }


    public String getUpdateTimeDesc()
    {
        if (this.updateTime != null)
        {
            return Util.getTimeByDate(this.updateTime);
        }
        else
        {
            return null;
        }
    }


    public void setUpdateTimeDesc(String updateTimeDesc)
    {
        this.updateTimeDesc = updateTimeDesc;
    }
}
