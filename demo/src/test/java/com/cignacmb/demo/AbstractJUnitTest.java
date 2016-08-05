package com.cignacmb.demo;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @类名： AbstractJUnitTest.java 
 * @描述：单元测试抽象类，所有模块的单元测试类均继承此类，统一读取配置文件
 * @作者： mxyanx
 * @修改日期： 2014年6月24日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:conf-spring/applicationContext.xml","classpath:conf-spring/applicationContext-mvc.xml","classpath:conf-spring/applicationContext-quartz.xml" })
public abstract class AbstractJUnitTest extends AbstractJUnit4SpringContextTests {

}
