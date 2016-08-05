package com.cignacmb.example.dao.biz;

import java.util.List;
import java.util.Map;

import com.cignacmb.example.model.UserModel;

/**
 * 
 * @类名： UserBizMapper.java
 * @描述：
 * @作者： pxanxx
 * @修改日期： 2014年6月23日
 */
public interface UserBizMapper
{
    /**
     * 
     * 功能描述：检查用户名和密码是否正确
     * 
     * @param paramMap
     * @return
     */
    public abstract Integer checkExistByNameAndPassword(Map<String, String> paramMap);


    /**
     * 
     * 功能描述：检查用户名是否存在
     * 
     * @param name
     * @return
     */
    public abstract Integer checkExistByName(String name);


    /**
     * 
     * 功能描述：查询所用用户列表
     * 
     * @return
     */
    public abstract List<UserModel> findAll();

}
