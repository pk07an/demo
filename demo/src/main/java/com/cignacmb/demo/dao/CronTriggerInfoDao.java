package com.cignacmb.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cignacmb.demo.bo.CronTriggerInfo;

/**
 * 
 * @类名： CronTriggerInfoDao.java 
 * @描述：触发器DAO
 * @作者： mxyanx
 * @修改日期： 2014年11月14日
 */
@Repository
public class CronTriggerInfoDao{
    
    @Autowired
    private SqlSession sqlSession;
    
	public List<CronTriggerInfo> getCronTriggerList(){
		return this.sqlSession.selectList("getCronTriggerList");
	}
}
