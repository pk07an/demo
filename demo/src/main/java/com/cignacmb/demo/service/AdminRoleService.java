package com.cignacmb.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cignacmb.demo.bo.AdminRole;
import com.cignacmb.demo.dao.AdminRoleDao;
import com.cignacmb.demo.exception.BusinessException;

/**
 * 
 * @类名： AdminRoleService.java
 * @描述：admin角色Service
 * @作者： mxyanx
 * @修改日期： 2014年9月1日
 */
@Service
public class AdminRoleService
{
    @Autowired
    private AdminRoleDao adminRoleDao;


    public Map<String, Object> getAdminRoleList(Integer pageNumber, Integer pageSize, String name, String code)
    {
        List<AdminRole> roleList = this.adminRoleDao.getAdminRoleList(name, code);
        Integer fromRecord = (pageNumber - 1) * pageSize;
        Integer endRecord = roleList.size() < fromRecord + pageSize ? roleList.size() : fromRecord + pageSize;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", roleList == null ? null : roleList.subList(fromRecord, endRecord));
        map.put("total", roleList == null ? 0 : roleList.size());

        return map;
    }


    public List<AdminRole> getAllAdminRole()
    {
        return this.adminRoleDao.getAdminRoleList(null, null);
    }


    public AdminRole getAdminRoleById(Integer id)
    {
        return this.adminRoleDao.getAdminRoleById(id);
    }

    public void updateAdminRole(AdminRole adminRole)
    {
        this.adminRoleDao.updateAdminRole(adminRole);
    }

    public void deleteAdminRoleById(Integer id)
    {
        this.adminRoleDao.deleteAdminRoleById(id);
    }

    public void saveRoleInfo(Integer id, String name, String code, String description) throws BusinessException
    {
        AdminRole adminRole = new AdminRole();
        adminRole.setCode(code);
        adminRole.setDescription(description);
        adminRole.setName(name);
        if (id == null)
        {
            adminRole.setCreateTime(new Date());
            // 插入到数据库之前检查数据库中是否已存在
            int count = this.adminRoleDao.getAdminRoleCount(code);
            if (count > 0)
            {
                throw new BusinessException("角色代码已存在！");
            }
            this.adminRoleDao.insertAdminRole(adminRole);
        }
        else
        {
            adminRole.setUpdateTime(new Date());
            adminRole.setId(id);
            this.adminRoleDao.updateAdminRole(adminRole);
        }
    }
}
