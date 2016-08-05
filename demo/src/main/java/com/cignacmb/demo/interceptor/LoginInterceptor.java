package com.cignacmb.demo.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cignacmb.demo.bo.AdminUser;
import com.cignacmb.demo.common.Constants;
import com.cignacmb.demo.service.AdminUserService;
import com.cignacmb.demo.utils.Util;

/**
 * 
 * @类名： LoginInterceptor.java
 * @描述：登录态校验的拦截器，首次登陆写Cookie，否则校验Cookie中登录名的合法性
 * @作者： mxyanx
 * @修改日期： 2014年8月19日
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter
{

    private static final Logger logger = Logger.getLogger(LoginInterceptor.class);

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private AdminUser adminUser;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException
    {
        String cookieUserName = Util.getUserNameFromCookie(request, Constants.USER_NAME_PREFIX + Constants.USER_NAME);
        String userName = request.getParameter("userName");
        String password = request.getParameter("pwd");
        // cookie中已有用户名，则需要比较userName
        if (StringUtils.isNotBlank(cookieUserName))
        {
            // 如果用户名和cookie中的用户名不相等，则需要去数据库校验
            if (StringUtils.isNotEmpty(userName) && !userName.equalsIgnoreCase(cookieUserName))
            {
                // 去数据库校验
                boolean flag = this.doUserCheck(userName, password);
                if (flag)
                {
                    Util.addUserNameToCookie(request, response, Constants.USER_NAME_PREFIX + Constants.USER_NAME, userName, 2 * 60 * 60);
                    adminUser.setUserName(userName);
                    return true;
                }
                else
                {
                    request.setAttribute("errorMsg", "用户名或密码错误！");
                    try
                    {
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                    catch (ServletException e)
                    {
                        logger.info("登录失败！", e);
                    }
                    return false;
                }
            }
            else
            {
                return true;
            }
        }
        else
        {
            // cookie失效或者首次登录，需要去数据库校验
            if (StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(password))
            {
                boolean flag = this.doUserCheck(userName, password);
                if (flag)
                {
                    Util.addUserNameToCookie(request, response, Constants.USER_NAME_PREFIX + Constants.USER_NAME, userName, 2 * 60 * 60);
                    adminUser.setUserName(userName);
                    return true;
                }
                else
                {
                    request.setAttribute("errorMsg", "用户名或密码错误！");
                    try
                    {
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                    catch (ServletException e)
                    {
                        logger.info("登录失败！", e);
                    }
                    return false;
                }
            }
            else
            {
                request.setAttribute("errorMsg", "用户名或密码失效，请重新登录系统！");
                try
                {
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
                catch (ServletException e)
                {
                    logger.info("登录失败！", e);
                }
                return false;
            }
        }
    }


    private boolean doUserCheck(String userName, String password)
    {
        adminUser = this.adminUserService.getAdminUserByName(userName, password);
        return adminUser == null ? false : true;
    }

}
