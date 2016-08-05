package com.cignacmb.demo.common;


/**
 * 
 * @类名： AdminRoleEnum.java 
 * @描述：系统角色枚举类
 * @作者： mxyanx
 * @修改日期： 2014年11月14日
 */
public enum AdminRoleEnum
{
    
    ADMIN("admin","管理员"),
    AUDITOR("assessor","审核员"),
    USER("user","一般用户");
    
    private String roleCode;
    
    private String roleDesc;
    
    private AdminRoleEnum(String roleCode,String roleDesc){
        this.roleCode = roleCode;
        this.roleDesc = roleDesc;
    }
    
    public static String getRoleDescByName(String name){
        for(AdminRoleEnum adminRole :AdminRoleEnum.values()){
            if(name.equalsIgnoreCase(adminRole.roleCode)){
                return adminRole.roleDesc;
            }
        }
        return null;
    }
    
    
    public static void main(String[] args)
    {
        System.out.println(getRoleDescByName("user"));
    }

}
