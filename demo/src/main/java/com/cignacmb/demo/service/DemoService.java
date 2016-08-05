package com.cignacmb.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 
 * @类名： DemoService.java 
 * @描述：测试Service，用来打印出变量，以说明在job中也能注入SpringMVC的Service
 * @作者： mxyanx
 * @修改日期： 2014年11月14日
 */
@Service
public class DemoService
{
    @Value("${test.value}")
    private String testValue;
    
    public void printVariable()
    {
        System.out.println("test Value is:"+testValue);
    }
}
