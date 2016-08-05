package com.cignacmb.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cignacmb.demo.bo.AdminMenu;

/**
 * 
 * @类名： AdminMenuDao.java 
 * @描述：系统菜单操作DAO
 * @作者： mxyanx
 * @修改日期： 2014年11月14日
 */
@Repository
public class AdminMenuDao {
    @Autowired
    private SqlSession sqlSession;
    
    public void insertAdminMenu(AdminMenu adminMenu){
        this.sqlSession.insert("insertAdminMenu", adminMenu);
    }
    
    public void updateAdminMenu(AdminMenu adminMenu){
    	this.sqlSession.update("updateAdminMenu", adminMenu);
    }
    
    public void deleteAdminMenuByMenuId(Long menuId){
    	this.sqlSession.delete("deleteAdminMenuByMenuId",menuId);
    }
    
    public AdminMenu getAdminMenuByName(String menuName){
        return this.sqlSession.selectOne("getAdminMenuByName",menuName);
    }
    
    public AdminMenu getAdminMenuByMenuId(Long menuId){
        return this.sqlSession.selectOne("getAdminMenuByMenuId",menuId);
    }
    
    public List<AdminMenu> getChildrenByParentId(Long menuId){
        return this.sqlSession.selectList("getChildrenByParentId",menuId);
    }
    
    public List<AdminMenu> getAdminMenuList(){
        return this.sqlSession.selectList("getAdminMenuList");
    }
}