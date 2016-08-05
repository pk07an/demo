package com.cignacmb.example.web.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cignacmb.example.model.UserModel;
import com.cignacmb.example.service.LoginService;
import com.cignacmb.example.web.validator.UserValidator;
import com.cignacmb.example.web.vo.UserVo;

/**
 * @类名： LoginController.java
 * @描述：
 * @作者： pxanxx
 * @修改日期： 2014年6月20日
 */
@Controller
public class LoginController
{

    private static final Logger log = Logger.getLogger(LoginController.class);

    @Autowired
    private LoginService loginServiceImpl;


    @InitBinder
    public void initBinder(DataBinder binder)
    {
        binder.setValidator(new UserValidator());
    }


    /**
     * 
     * 功能描述：进入主页面
     * 
     * @return 方式一，返回视图名称
     */
    @RequestMapping(value = "/")
    public String login()
    {
        log.info("进入主页面");
        return "login";
    }


    /**
     * 
     * 功能描述：用户登录action
     * 
     * @param modle
     * @param user
     * @return 方式二，返回 ModelAndView
     */
    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public ModelAndView main(@Validated
    UserVo user, BindingResult result)
    {
        ModelAndView modelAndView = new ModelAndView();
        log.info("用户登录action");
        if (result.hasErrors())
        {
            List<FieldError> errorList = result.getFieldErrors();
            StringBuilder sb = new StringBuilder();
            for (FieldError fieldError : errorList)
            {
                sb.append(fieldError.getDefaultMessage()).append("<br/>");
            }
            modelAndView.addObject("errorMsg", sb.toString());
            modelAndView.setViewName("login");
        }
        else
        {
            boolean isExist = loginServiceImpl.checkLogin(user.getName(), user.getPassword());
            if (isExist)
            {
                // modelAndView.addObject("name", user.getName());
                List<UserModel> userList = loginServiceImpl.findAll();
                modelAndView.addObject("userList", userList);
                modelAndView.addObject(user);
                modelAndView.setViewName("main");
            }
            else
            {
                modelAndView.addObject("errorMsg", "不存在该用户");
                modelAndView.setViewName("login");
            }
        }
        return modelAndView;

    }


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainGet()
    {
        log.info("用户登录action---GET访问");
        return "login";
    }


    /**
     * 
     * 功能描述：进入注册页面
     * 
     * @return 方式三，无返回，根据url查找对应视图
     */
    @RequestMapping(value = "/regedit")
    public void regedit()
    {
        log.info("进入注册页面");
    }


    /**
     * 
     * 功能描述：注册操作action
     * 
     * @return 方式四，返回ModelMap，根据url查找对应视图
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    ModelMap create(UserVo userVo)
    {
        log.info("注册操作action");
        int status = loginServiceImpl.regedit(userVo.getName(), userVo.getPassword());
        ModelMap map = new ModelMap();
        map.put("status", status);
        return map;
    }

}
