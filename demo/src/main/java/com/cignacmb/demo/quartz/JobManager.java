package com.cignacmb.demo.quartz;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.cignacmb.demo.bo.CronTriggerInfo;
import com.cignacmb.demo.dao.CronTriggerInfoDao;
import com.cignacmb.demo.service.DemoService;

/**
 * 
 * @类名： JobManager.java
 * @描述：job管理类，每当配置文件修改时重新管理配置文件到数据库，并且初始化时获取access_token到数据库
 * @作者： mxyanx
 * @修改日期： 2014年9月28日
 */
@Component
public class JobManager
{

    private static final Logger logger = Logger.getLogger(JobManager.class);

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private CronTriggerInfoDao cronTriggerInfoDao;

    @Autowired
    @Qualifier("trigger")
    private Trigger trigger;

    @Autowired
    private DemoService demoService;


    @PostConstruct
    public void start() throws SchedulerException
    {
        List<CronTriggerInfo> cronTriggersList = this.cronTriggerInfoDao.getCronTriggerList();
        for (CronTriggerInfo cronTriggerInfo : cronTriggersList)
        {
            if (cronTriggerInfo.getTriggerName().equalsIgnoreCase("trigger"))
            {
                scheduler.rescheduleJob(cronTriggerInfo.getTriggerName(), Scheduler.DEFAULT_GROUP, trigger);
            }
        }
        this.demoService.printVariable();
    }
}
