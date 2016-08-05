package com.cignacmb.example.web.vo;

import java.io.Serializable;

public class UserVo implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -3878302600414183571L;
    private String name; // 用户名
    private String password; // 密码


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public String getPassword()
    {
        return password;
    }


    public void setPassword(String password)
    {
        this.password = password;
    }

}
