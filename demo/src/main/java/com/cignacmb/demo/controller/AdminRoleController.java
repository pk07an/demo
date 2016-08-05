package com.cignacmb.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cignacmb.demo.bo.AdminRole;
import com.cignacmb.demo.common.ResultObject;
import com.cignacmb.demo.exception.BusinessException;
import com.cignacmb.demo.service.AdminRoleService;

/**
 * 
 * @类名： AdminRoleController.java
 * @描述：admin角色控制器
 * @作者： mxyanx
 * @修改日期： 2014年9月1日
 */
@Controller
public class AdminRoleController
{

    @Autowired
    private AdminRoleService adminRoleService;


    @RequestMapping(value = "jump_role_list.xhtml", method = RequestMethod.GET)
    public ModelAndView jumpRoleList(@RequestParam(value = "pageNum", required = false)
    String pageNum)
    {
        return new ModelAndView("role_list").addObject("pageNum", pageNum);
    }


    @RequestMapping(value = "jump_role_edit.xhtml", method = RequestMethod.GET)
    public ModelAndView jumpRoleEdit(@RequestParam(value = "operType")
    String operType, @RequestParam(value = "id", required = false)
    String id, @RequestParam(value = "pageNum", required = false)
    String pageNum)
    {
        return new ModelAndView("role_edit").addObject("operType", operType).addObject("id", id).addObject("pageNum", pageNum);
    }


    @RequestMapping(value = "/load_role_list.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject loadRoleList(@RequestParam(value = "pageNumber", defaultValue = "1")
    Integer pageNumber, @RequestParam(value = "pageSize", defaultValue = "10")
    Integer pageSize, @RequestParam(value = "name", required = false)
    String name, @RequestParam(value = "code", required = false)
    String code)
    {
        Map<String, Object> roleList = this.adminRoleService.getAdminRoleList(pageNumber, pageSize, name, code);
        return new ResultObject(roleList);
    }


    @RequestMapping(value = "/save_role_info.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject saveRoleInfo(@RequestParam(value = "id", required = false)
    Integer id, @RequestParam(value = "name", required = false)
    String name, @RequestParam(value = "code", required = true)
    String code, @RequestParam(value = "description", required = false)
    String description)
    {
        try
        {
            this.adminRoleService.saveRoleInfo(id, name, code, description);
        }
        catch (BusinessException e)
        {
            return new ResultObject(-1, e.getErrorMsg());
        }
        return new ResultObject();
    }


    @RequestMapping(value = "/delete_role_by_id.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject deleteRoleById(@RequestParam(value = "id")
    Integer id)
    {
        this.adminRoleService.deleteAdminRoleById(id);
        return new ResultObject();
    }


    @RequestMapping(value = "/get_role_by_id.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject getAdminRoleById(@RequestParam(value = "id")
    Integer id)
    {
        AdminRole adminRole = this.adminRoleService.getAdminRoleById(id);
        return new ResultObject(adminRole);
    }

}
