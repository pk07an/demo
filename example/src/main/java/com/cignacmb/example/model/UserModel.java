package com.cignacmb.example.model;

import java.util.Date;

import org.springframework.stereotype.Repository;

public class UserModel
{
    private Integer id;

    private String password;

    private String name;

    private Date createDate;


    public Integer getId()
    {
        return id;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }


    public String getPassword()
    {
        return password;
    }


    public void setPassword(String password)
    {
        this.password = password == null ? null : password.trim();
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name == null ? null : name.trim();
    }


    public Date getCreateDate()
    {
        return createDate;
    }


    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }
}
