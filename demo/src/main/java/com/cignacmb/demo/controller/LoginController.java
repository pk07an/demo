package com.cignacmb.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cignacmb.demo.bo.AdminUser;
import com.cignacmb.demo.common.Constants;
import com.cignacmb.demo.common.ResultObject;
import com.cignacmb.demo.common.TreeNode;
import com.cignacmb.demo.service.AdminMenuService;
import com.cignacmb.demo.service.AdminUserService;
import com.cignacmb.demo.utils.Util;

/**
 * 登陆控制器
 * 
 * @author j1deng
 */
@Controller
public class LoginController
{
    
    @Autowired
    private AdminMenuService adminMenuService; 
    
    @Autowired
    private AdminUserService adminUserService;

	@Autowired
	private AdminUser adminUser;
    
    @RequestMapping(value = "/login.xhtml", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "userName")String userName, @RequestParam(value = "pwd")String pwd)
    {
        List<TreeNode> adminMenu = adminMenuService.getAdminMenu(userName);
        return new ModelAndView("index").addObject("userName",userName).addObject("adminMenu", adminMenu);
    }
    

    
    
    /**
     * 退出登陆
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logOut.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject logOut(HttpServletRequest request,HttpServletResponse response)
    {
    	Util.clearCookie(request, response, Constants.USER_NAME_PREFIX + Constants.USER_NAME);
    	ResultObject resultObject = new ResultObject();
    	resultObject.setData("SUCCESS");
        return resultObject;
    }
    
    /*临时增加*/
    @RequestMapping(value = "jump_demo.xhtml", method = RequestMethod.GET)
    public ModelAndView jumpResMessageList()
    {
        return new ModelAndView("demo");
    }
}
