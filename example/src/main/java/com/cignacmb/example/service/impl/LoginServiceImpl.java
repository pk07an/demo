package com.cignacmb.example.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cignacmb.example.dao.biz.UserBizMapper;
import com.cignacmb.example.dao.pub.UserModelMapper;
import com.cignacmb.example.model.UserModel;
import com.cignacmb.example.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService
{
    @Autowired
    private UserBizMapper userBizMapper;
    @Autowired
    private UserModelMapper userModelMapper;


    @Override
    public boolean checkLogin(String name, String password)
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("name", name);
        paramMap.put("password", password);
        Integer count = userBizMapper.checkExistByNameAndPassword(paramMap);
        if (null != count && count.intValue() > 0)
        {
            return true;
        }
        return false;
    }


    @Override
    public int regedit(String name, String password)
    {
        int flag = 0;
        if (userBizMapper.checkExistByName(name) == 0)
        {
            UserModel model = new UserModel();
            model.setName(name);
            model.setPassword(password);
            flag = userModelMapper.insertSelective(model);
        }
        else
        {
            flag = 99;
        }

        return flag;
    }


    @Override
    public List<UserModel> findAll()
    {

        return userBizMapper.findAll();
    }
}
