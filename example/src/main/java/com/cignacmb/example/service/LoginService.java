package com.cignacmb.example.service;

import java.util.List;

import com.cignacmb.example.model.UserModel;

/**
 * 
 * @类名： LoginService.java
 * @描述：
 * @作者： pxanxx
 * @修改日期： 2014年6月23日
 */
public interface LoginService
{

    public abstract boolean checkLogin(String name, String password);


    public abstract int regedit(String name, String password);


    public abstract List<UserModel> findAll();
}
