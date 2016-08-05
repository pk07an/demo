package com.cignacmb.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cignacmb.demo.bo.AdminUser;

/**
 * 
 * @类名： AdminUserDao.java 
 * @描述：admin登录用户表的操作类
 * @作者： mxyanx
 * @修改日期： 2014年8月19日
 */
@Repository
public class AdminUserDao {
    
    @Autowired
    private SqlSession sqlSession;
    
    public AdminUser getAdminUserByName(Map<String,Object> params){
        return (AdminUser)this.sqlSession.selectOne("getAdminUserByName",params);
    }
    
    public void insertAdminUser(AdminUser adminUser){
        this.sqlSession.insert("insertAdminUser",adminUser);
    }
    
    public List<AdminUser> getAdminUserList(String userName){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userName", userName);
        return this.sqlSession.selectList("getAdminUserList",params);
    }
    
    public AdminUser getAdminUserById(Integer id){
        return this.sqlSession.selectOne("getAdminUserById",id);
    }
    
    public void deleteAdminUserById(Integer id){
        this.sqlSession.delete("deleteAdminUserById",id);
    }
    
    public void updateAdminUser(AdminUser adminUser){
        this.sqlSession.update("updateAdminUser",adminUser);
    }
}