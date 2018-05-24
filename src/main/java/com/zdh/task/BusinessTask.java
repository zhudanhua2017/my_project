package com.zdh.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zdh.bean.SysParam;
import com.zdh.constant.SysParamKeyConst;
import com.zdh.dao.BusinessDao;
import com.zdh.dao.SysParamDao;

/**
 * 商户相关的定时任务
 * @author Administrator
 *
 */
@Component("BusinessTask")
public class BusinessTask {
	
	private static final Logger logger = LoggerFactory.getLogger(BusinessTask.class);
	
	@Resource
	private BusinessDao businessDao;
	
	@Resource
	private SysParamDao sysParamDao;
	
	/**
	 * 同步已售数量
	 */
	public void synNumber(){
		logger.info("synNumber start");
		// 先获取上次同步的时间(最后同步时间)
		SysParam sysParam = sysParamDao.selectByKey(SysParamKeyConst.LAST_SYNC_NUM_TIME);
		Map<String,Date> map = new HashMap<>();
		map.put("startTime", sysParam.getParamValue());
		// 以当前系统时间做为同步的截止时间，同时也做为下次同步的起始时间
		Date endTime = new Date();
		map.put("endTime", endTime);
		// 按这样起始时间-结束时间同步：商户对应的【已售数量】number
		// 如果起始时间为NULL，那说明是第一次同步，需要将历史数据全步同步，一直同步到当前系统时间为止。
		businessDao.updateNumber(map);
		// 将当前这个系统时间更新到系统参数表中，做为下次同步的起始时间
		SysParam sysParamForUpdate = new SysParam();
		sysParamForUpdate.setParamKey(SysParamKeyConst.LAST_SYNC_NUM_TIME);
		sysParamForUpdate.setParamValue(endTime);
		sysParamDao.updateByKey(sysParamForUpdate);
		logger.info("synNumber end");
	}
	
	/**
	 * 同步星级
	 */
	public void synStar(){
		logger.info("synStar start");
		// 先获取上次同步的时间(最后同步时间)
		SysParam sysParam = sysParamDao.selectByKey(SysParamKeyConst.LAST_SYNC_STAR_TIME);
		Map<String,Date> map = new HashMap<>();
		map.put("startTime", sysParam.getParamValue());
		// 以当前系统时间做为同步的截止时间，同时也做为下次同步的起始时间
		Date endTime = new Date();   
		map.put("endTime", endTime);
		// 按这样起始时间-结束时间同步：商户对应的【星星总数】、【评论总次数】
		// 如果起始时间为NULL，那说明是第一次同步，需要将历史数据全步同步，一直同步到当前系统时间为止。
		businessDao.updateStar(map);
		// 将当前这个系统时间更新到系统参数表中，做为下次同步的起始时间
		SysParam sysParamForUpdate = new SysParam();
		sysParamForUpdate.setParamKey(SysParamKeyConst.LAST_SYNC_STAR_TIME);
		sysParamForUpdate.setParamValue(endTime);
		sysParamDao.updateByKey(sysParamForUpdate);
		logger.info("synStar end");
	}
}
