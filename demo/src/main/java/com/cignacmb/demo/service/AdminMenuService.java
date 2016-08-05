package com.cignacmb.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cignacmb.demo.bo.AdminMenu;
import com.cignacmb.demo.bo.AdminUser;
import com.cignacmb.demo.common.TreeNode;
import com.cignacmb.demo.dao.AdminMenuDao;
import com.cignacmb.demo.utils.TreeUtils;

/**
 * 
 * @类名： AdminMenuService.java 
 * @描述：admin菜单服务类
 * @作者： mxyanx
 * @修改日期： 2014年8月19日
 */
@Service
public class AdminMenuService
{

    @Autowired
    private AdminMenuDao adminMenuDao;

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 
     * 功能描述：返回菜单列表
     * @param userName
     * @return
     */
    public List<TreeNode> getAdminMenu(String userName)
    {
        List<AdminMenu> list = this.adminMenuDao.getAdminMenuList();
        AdminUser adminUser = this.adminUserService.getAdminUserByName(userName,null);
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        TreeNode node;
        for (AdminMenu adminMenu : list)
        {
            node = new TreeNode();
            node.setId(adminMenu.getMenuId());
            node.setName(adminMenu.getName());
            node.setParentId(adminMenu.getParentId());
            node.setHref(adminMenu.getUrl());
            node.setOrder(adminMenu.getMenuOrder());
            node.setDescription(adminMenu.getDescription());
            node.setRoles(this.getMenuRoles(adminMenu.getRole()));
            //找到所有符合条件的叶子节点
            if (node.getParentId() > 0 && (!this.isIncludeUserRole(node, adminUser.getRole())) && StringUtils.isNotBlank(node.getHref()))
                continue;
            nodeList.add(node);
        }
        List<TreeNode> topMenuList = TreeUtils.buildMenuTree(nodeList);
        return removeTopNodeByUserRole(topMenuList);

    }
    
    /**
     * 
     * 功能描述：返回菜单资源管理列表数据
     * @param userName
     * @return
     */
    public List<TreeNode> getAdminMenuData(String userName)
    {
        List<AdminMenu> list = this.adminMenuDao.getAdminMenuList();
        AdminUser adminUser = this.adminUserService.getAdminUserByName(userName,null);
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        TreeNode node;
        for (AdminMenu adminMenu : list)
        {
            node = new TreeNode();
            node.setId(adminMenu.getMenuId());
            node.setName(adminMenu.getName());
            node.setParentId(adminMenu.getParentId());
            node.setHref(adminMenu.getUrl());
            node.setOrder(adminMenu.getMenuOrder());
            node.setDescription(adminMenu.getDescription());
            node.setRoles(this.getMenuRoles(adminMenu.getRole()));
            //找到所有符合条件的叶子节点
            if (node.getParentId() > 0 && (!this.isIncludeUserRole(node, adminUser.getRole())) && StringUtils.isNotBlank(node.getHref()))
                continue;
            nodeList.add(node);
        }
        List<TreeNode> topMenuList = TreeUtils.buildMenuTree(nodeList);
        return topMenuList;

    }


   


    //判断菜单叶子节点是否有角色信息，如果有，是否跟用户角色相等，admin用户除外
    private boolean isIncludeUserRole(TreeNode node, String userRole)
    {
        boolean flag = false;
        //如果叶子菜单没有角色信息则不显示
        if (node.getRoles() == null || node.getRoles().size() == 0)
            return false;
        //如果用户是admin用户，则直接返回true，可以看到所有菜单
        if(userRole.equalsIgnoreCase("admin")){
            return true;
        }
        List<String> menuRoles = node.getRoles();
        for (String s : menuRoles)
        {
            if (s.equalsIgnoreCase(userRole))
            {
                flag = true;
                break;
            }
        }
        return flag;
    }


    // 根据菜单角色返回角色列表，一个菜单可能有多个角色，但一个用户只有一个角色
    private List<String> getMenuRoles(String menuRoleStr)
    {
        List<String> menuRoleList = new ArrayList<String>();
        if (StringUtils.isNotEmpty(menuRoleStr))
        {
            String[] strs = StringUtils.split(menuRoleStr, ",");
            for (String s : strs)
            {
                menuRoleList.add(s);
            }
        }
        return menuRoleList;
    }


    //删除没有子菜单的一级菜单
    private List<TreeNode> removeTopNodeByUserRole(List<TreeNode> nodeList)
    {
        Iterator<TreeNode> iterator = nodeList.iterator();
        while (iterator.hasNext())
        {
            TreeNode next = iterator.next();
            if (next.getChildren() == null || next.getChildren().isEmpty())
            {
                iterator.remove();
            }
        }
        return nodeList;
    }
    
    public void insertAdminMenu(AdminMenu adminMenu){
        this.adminMenuDao.insertAdminMenu(adminMenu);
    }
    
    public void updateAdminMenu(AdminMenu adminMenu){
    	this.adminMenuDao.updateAdminMenu(adminMenu);
    }
    
    public void deleteAdminMenuByMenuId(Long menuId){
    	this.adminMenuDao.deleteAdminMenuByMenuId(menuId);
    }
    
    public AdminMenu getAdminMenuByName(String menuName){
        return this.adminMenuDao.getAdminMenuByName(menuName);
    }
    
    public List<AdminMenu> getChildrenByParentId(Long menuId){
        return this.adminMenuDao.getChildrenByParentId(menuId);
    }
    
    public AdminMenu getAdminMenuByMenuId(Long menuId){
        return this.adminMenuDao.getAdminMenuByMenuId(menuId);
    }

}
