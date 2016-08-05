package com.cignacmb.demo.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @类名： TreeNode.java
 * @描述：树节点通用类
 * @作者： mxyanx
 * @修改日期： 2014年8月19日
 */
public class TreeNode
{

    private Long id;
    private String name;
    private String href;
    private Long parentId;
    private Integer order;
    private String description;
    private List<String> roles;
    private List<TreeNode> children = new ArrayList<TreeNode>();
    private TreeNode parentNode;
    private String menuType;
    
    private String properties;
    private String flag;
    
    private String state;
    
    


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }



    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public String getHref()
    {
        return href;
    }


    public void setHref(String href)
    {
        this.href = href;
    }


    public void addChild(TreeNode node)
    {
        children.add(node);
    }


    public List<TreeNode> getChildren()
    {
        return children;
    }


    public void setChildren(List<TreeNode> children)
    {
        this.children = children;
    }


    public Long getParentId()
    {
        return parentId;
    }


    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }


   


    public Integer getOrder()
    {
        return order;
    }


    public void setOrder(Integer order)
    {
        this.order = order;
    }


    public String getDescription()
    {
        return description;
    }


    public void setDescription(String description)
    {
        this.description = description;
    }


    public List<String> getRoles()
    {
        return roles;
    }


    public void setRoles(List<String> roles)
    {
        this.roles = roles;
    }


    public void deleteChildNodes()
    {
        this.children.clear();
    }


    public TreeNode getParentNode()
    {
        return parentNode;
    }


    public void setParentNode(TreeNode parentNode)
    {
        this.parentNode = parentNode;
    }


    public String getMenuType()
    {
        return menuType;
    }


    public void setMenuType(String menuType)
    {
        this.menuType = menuType;
    }


    public String getProperties()
    {
        return properties;
    }


    public void setProperties(String properties)
    {
        this.properties = properties;
    }


    public String getFlag()
    {
        return flag;
    }


    public void setFlag(String flag)
    {
        this.flag = flag;
    }


    public String getState()
    {
        return state;
    }


    public void setState(String state)
    {
        this.state = state;
    }
    
    
    
    
    
    
}
