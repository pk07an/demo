package com.cignacmb.demo.bo;

import java.util.Date;

import com.cignacmb.demo.common.AdminRoleEnum;
import com.cignacmb.demo.utils.Util;

/**
 * 
 * @类名： AdminRole.java 
 * @描述：系统 角色对象
 * @作者： mxyanx
 * @修改日期： 2014年11月14日
 */
public class AdminRole
{
    private Integer id;

    private String name;

    private String code;

    private String codeDesc;

    private String description;

    private Date createTime;

    private String createTimeDesc;

    private Date updateTime;

    private String updateTimeDesc;


    public Integer getId()
    {
        return id;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name == null ? null : name.trim();
    }


    public String getCode()
    {
        return code;
    }


    public void setCode(String code)
    {
        this.code = code == null ? null : code.trim();
    }


    public String getDescription()
    {
        return description;
    }


    public void setDescription(String description)
    {
        this.description = description == null ? null : description.trim();
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


    public String getCodeDesc()
    {
        return AdminRoleEnum.getRoleDescByName(name);
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

}
