package com.cignacmb.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cignacmb.demo.bo.AdminRole;

/**
 * 
 * @类名： AdminRoleDao.java
 * @描述：admin_role角色表对应的DAO
 * @作者： mxyanx
 * @修改日期： 2014年9月1日
 */
@Repository
public class AdminRoleDao
{

    @Autowired
    private SqlSession sqlSession;


    public Integer insertAdminRole(AdminRole adminRole)
    {
        return this.sqlSession.insert("insertAdminRole", adminRole);
    }


    public Integer updateAdminRole(AdminRole adminRole)
    {
        return this.sqlSession.update("updateAdminRole", adminRole);
    }


    public Integer deleteAdminRoleById(Integer id)
    {
        return this.sqlSession.delete("deleteAdminRoleById", id);
    }


    public AdminRole getAdminRoleById(Integer id)
    {
        return this.sqlSession.selectOne("getAdminRoleById", id);
    }
    
    public List<AdminRole> getAdminRoleList(String name,String code){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("name", name);
        params.put("code", code);
        return this.sqlSession.selectList("getAdminRoleList",params);
    }
    
    public List<AdminRole> getAllKindsRole(){
        return this.sqlSession.selectList("getAllKindsRole");
    }
    
    public int getAdminRoleCount(String code){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("code", code);
        Integer count = this.sqlSession.selectOne("getAdminRoleCount",params);
        return count == null ? 0 : count;
    }
}
