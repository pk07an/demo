package com.cignacmb.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cignacmb.demo.bo.AdminMenu;
import com.cignacmb.demo.bo.AdminUser;
import com.cignacmb.demo.common.Constants;
import com.cignacmb.demo.common.ResultObject;
import com.cignacmb.demo.common.TreeNode;
import com.cignacmb.demo.service.AdminMenuService;
import com.cignacmb.demo.service.AdminRoleService;
import com.cignacmb.demo.service.AdminUserService;
import com.cignacmb.demo.utils.Util;

/**
 * 
 * @类名： AdminMenuController.java 
 * @描述：系统菜单控制器
 * @作者： mxyanx
 * @修改日期： 2014年9月5日
 */
@Controller
public class AdminMenuController
{
    
    private final static Logger logger = Logger.getLogger(AdminMenuController.class);

    @Autowired
    private AdminMenuService adminMenuService; 
    
    @Autowired
    private AdminUserService adminUserService;
    
    @Autowired
    private AdminRoleService adminRoleService;

    /**
     * 
     * 功能描述：跳转到用户列表页面
     * 
     * @return
     */
    @RequestMapping(value = "jump_admin_menu_tree.xhtml", method = RequestMethod.GET)
    public ModelAndView jumpAdminMenuList()
    {
        return new ModelAndView("admin_menu_tree");
    }
    
    /**
     * 
     * 功能描述：加载菜单资源数据
     * 
     * @return
     */
    @RequestMapping(value = "loadAdminMenuDatas.json", method = RequestMethod.POST)
    public void loadAdminMenuDatas(HttpServletResponse response)
    {
    	 List<TreeNode> adminMenu = adminMenuService.getAdminMenuData("admin");
    	 ResultObject resultObject = new ResultObject();
    	 resultObject.setData(adminMenu);
    	 Util.render(response, JSON.toJSON(adminMenu).toString(), Util.JSON_TYPE);
    }
    
    /**
     * 
     * 功能描述：增加菜单OR更新菜单
     * 
     * @return
     * 
     * 
     */
    @RequestMapping(value = "addOrUpdateAdminMenu.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject addOrUpdateAdminMenu(@RequestParam(value="menuName",required=false) String menuName,
    		@RequestParam(value="parentId",required=false) String parentId,
    		@RequestParam(value="roles",required=false) String roles,
    		@RequestParam(value="description",required=false) String description,
    		@RequestParam(value="href",required=false) String href,
    		@RequestParam(value="opearType",required=false) String opearType,
    		@RequestParam(value="order",required=false) String order,
    		@RequestParam(value="menuId",required=false) String menuId,
    		@RequestParam(value="updateMenuId",required=false) String updateMenuId
    		){
    	AdminMenu adminMenu = new AdminMenu();
    	adminMenu.setMenuOrder(Integer.parseInt(order));
    	adminMenu.setUrl(href);
    	adminMenu.setName(menuName);
    	adminMenu.setRole(roles);
    	adminMenu.setDescription(description);
    	ResultObject resultObject = new ResultObject();
    	if("ADD_MENU".equalsIgnoreCase(opearType)){
    		AdminMenu menu = adminMenuService.getAdminMenuByName(menuName);
    		if(menu != null){
    			resultObject.setData("MENU_EXIST");
    			return resultObject;
    		}
    		AdminMenu menu2 = adminMenuService.getAdminMenuByMenuId(Long.valueOf(menuId));
    		if(menu2 != null){
    			resultObject.setData("MENUID_EXIST");
    			return resultObject;
    		}
    		adminMenu.setMenuId(Long.valueOf(menuId));
    		adminMenu.setParentId(Long.valueOf(parentId));
    		adminMenu.setCreateTime(new Date());
    		this.adminMenuService.insertAdminMenu(adminMenu);
    		resultObject.setData("ADD_SUCCESS");
    	}else if("UPDATE_MENU".equalsIgnoreCase(opearType)){
    		adminMenu.setUpdateTime(new Date());
    		adminMenu.setMenuId(Long.valueOf(updateMenuId));
    		this.adminMenuService.updateAdminMenu(adminMenu);
    		resultObject.setData("UPDATE_SUCCESS");
    	}else if("ADD_ZHUMENU".equalsIgnoreCase(opearType)){
    		AdminMenu menu = adminMenuService.getAdminMenuByName(menuName);
    		if(menu != null){
    			resultObject.setData("MENU_EXIST");
    			return resultObject;
    		}
    		AdminMenu menu2 = adminMenuService.getAdminMenuByMenuId(Long.valueOf(menuId));
    		if(menu2 != null){
    			resultObject.setData("MENUID_EXIST");
    			return resultObject;
    		}
    		adminMenu.setMenuId(Long.valueOf(menuId));
    		adminMenu.setParentId(0L);
    		adminMenu.setCreateTime(new Date());
    		this.adminMenuService.insertAdminMenu(adminMenu);
    		resultObject.setData("ADDZHU_SUCCESS");
    	}
    	return resultObject;
    }
    
    /**
     * 
     * 功能描述：删除菜单资源
     * 
     * @return
     */
    @RequestMapping(value = "deleteAdminMenu.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject deleteAdminMenu(@RequestParam(value="menuId",required=false) String menuId)
    {
    	ResultObject resultObject = new ResultObject();
    	this.adminMenuService.deleteAdminMenuByMenuId(Long.valueOf(menuId));
    	resultObject.setData("DEL_SUCCESS");
    	return resultObject;
    }
    
    /**
     * 
     * 功能描述：是否存在子菜单
     * 
     * @return
     */
    @RequestMapping(value = "existChildrenMenu.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject existChildrenMenu(@RequestParam(value="menuId",required=false) String menuId)
    {
    	ResultObject resultObject = new ResultObject();
    	List<AdminMenu> adminMenus = this.adminMenuService.getChildrenByParentId(Long.valueOf(menuId));
    	if(adminMenus.size() != 0){
    		resultObject.setData("EXIST");
    	}
    	return resultObject;
    }
    
    /**
     * 
     * 功能描述：跳转到资源管理页面
     * 
     * @return
     */
    @RequestMapping(value = "jumpMenuManage.xhtml", method = RequestMethod.GET)
    public ModelAndView jumpMenuManage(HttpServletRequest request)
    {
    	String userName = Util.getUserNameFromCookie(request, Constants.USER_NAME_PREFIX + Constants.USER_NAME);
    	AdminUser user = this.adminUserService.getAdminUserByName(userName,null);
        return new ModelAndView("adminMenuManage").addObject("user",user);
    }


    /**
     * 
     * 功能描述：异步加载列表数据
     * 
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "load_amin_menu_data.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject loadAdminMenuData()
    {
        return new ResultObject();
    }

}
