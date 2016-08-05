package com.cignacmb.demo.bo;

/**
 * 
 * @类名： CronTriggerInfo.java 
 * @描述：触发器对象
 * @作者： mxyanx
 * @修改日期： 2014年11月14日
 */
public class CronTriggerInfo
{

    private String cronExpression;

    private String timeZoneId;

    private String triggerName;

    private String triggerGroup;


    public String getCronExpression()
    {
        return cronExpression;
    }


    public void setCronExpression(String cronExpression)
    {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }


    public String getTimeZoneId()
    {
        return timeZoneId;
    }


    public void setTimeZoneId(String timeZoneId)
    {
        this.timeZoneId = timeZoneId == null ? null : timeZoneId.trim();
    }


    public String getTriggerName()
    {
        return triggerName;
    }


    public void setTriggerName(String triggerName)
    {
        this.triggerName = triggerName == null ? null : triggerName.trim();
    }


    public String getTriggerGroup()
    {
        return triggerGroup;
    }


    public void setTriggerGroup(String triggerGroup)
    {
        this.triggerGroup = triggerGroup == null ? null : triggerGroup.trim();
    }
}
