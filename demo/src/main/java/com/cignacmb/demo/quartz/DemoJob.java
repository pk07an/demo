package com.cignacmb.demo.quartz;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.cignacmb.demo.service.DemoService;

/**
 * 
 * @类名： DemoJob.java 
 * @描述：测试job，系统启动时和定时任务都可以用来输出功能
 * @作者： mxyanx
 * @修改日期： 2014年11月14日
 */
@Component
public class DemoJob extends QuartzJobBean{
    
    private static final Logger logger = Logger.getLogger(DemoJob.class);
    
    
    @Autowired
    private DemoService demoService;

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
	    logger.info("job start.......");
		this.demoService.printVariable();
	}
}
