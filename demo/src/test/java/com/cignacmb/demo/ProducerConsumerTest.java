package com.cignacmb.demo;

import javax.jms.Destination;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cignacmb.demo.jms.ProducerService;

/**
 * 
 * @类名： ProducerConsumerTest.java 
 * @描述：JMS消息发送测试类
 * @作者： mxyanx
 * @修改日期： 2015年3月18日
 */
public class ProducerConsumerTest extends AbstractJUnitTest
{
    @Autowired
    private ProducerService producerService;
    
    @Autowired
    @Qualifier("queueDestination")
    private Destination destination;


    @Test
    public void testSend()
    {
        for (int i = 3; i < 5; i++)
        {
            producerService.sendMessage(destination, "你好，生产者！这是消息：" + (i + 1));
        }
    }
}
