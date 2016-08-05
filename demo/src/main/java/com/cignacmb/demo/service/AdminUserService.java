package com.cignacmb.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cignacmb.demo.bo.AdminUser;
import com.cignacmb.demo.dao.AdminUserDao;
import com.cignacmb.demo.exception.BusinessException;

/**
 * 
 * @类名： AdminUserService.java 
 * @描述：admin用户服务类
 * @作者： mxyanx
 * @修改日期： 2014年8月19日
 */
@Service
public class AdminUserService
{
    @Autowired
    public AdminUserDao adminUserDao;
    
    /**
     * 
     * 功能描述：根据用户名、密码返回数据库中的用户数
     * @param userName
     * @param pwd
     * @return
     */
    public AdminUser getAdminUserByName(String userName,String pwd){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userName", userName);
        if(StringUtils.isNotEmpty(pwd)){
            params.put("password", pwd);
        }
        return this.adminUserDao.getAdminUserByName(params);
    }
    
    
    
    
    public Map<String,Object> getAdminUserList(Integer pageNumber, Integer pageSize,String userName){
        List<AdminUser> roleList = this.adminUserDao.getAdminUserList(userName);
        Integer fromRecord = (pageNumber - 1) * pageSize;
        Integer endRecord = roleList.size() < fromRecord + pageSize ? roleList.size() : fromRecord + pageSize;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", roleList == null ? null : roleList.subList(fromRecord, endRecord));
        map.put("total", roleList == null ? 0 : roleList.size());
        return map;
    }
    
    public AdminUser getAdminUserById(Integer id){
        return this.adminUserDao.getAdminUserById(id);
    }
    
    public void saveAdminUserInfo(Integer id, String userName, String password, String role) throws BusinessException{
        AdminUser adminUser = new AdminUser();
        adminUser.setUserName(userName);
        adminUser.setPassword(password);
        adminUser.setRole(role);
        if (id == null)
        {
            adminUser.setCreateTime(new Date());
            //新增用户之前检查是否用户名已存在
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("userName", userName);
            AdminUser user = this.adminUserDao.getAdminUserByName(params);
            if(user != null){
                throw new BusinessException("用户名已存在！");
            }
            this.adminUserDao.insertAdminUser(adminUser);
        }
        else
        {
            adminUser.setId(Long.valueOf(id));
            adminUser.setUpdateTime(new Date());
            this.adminUserDao.updateAdminUser(adminUser);
        }
    }
    
    public void deleteAdminUserById(Integer id){
        this.adminUserDao.deleteAdminUserById(id);
    }
    
    public void updateAdminUser(AdminUser adminUser){
        this.adminUserDao.updateAdminUser(adminUser);
    }
    
    
    /**
     * 
     * 功能描述：根据用户名返回用户角色
     * @param userName
     * @return
     */
    public String getUserRoleByName(String userName){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userName", userName);
        AdminUser adminUser = this.adminUserDao.getAdminUserByName(params);
        return adminUser.getRole();
    }
}
