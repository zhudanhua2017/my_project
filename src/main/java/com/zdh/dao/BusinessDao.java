package com.zdh.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zdh.bean.Business;

public interface BusinessDao {
	
	
	List<Business> selectByPage(Business business);
		
	Business selectById(Long id);
	
	int delete(Long id);
	
	int insert(Business business);
	
	int update(Business business);
	
	List<Business> selectLikeByPage(Business business);
	
	int updateStar(Map<String,Date> map);
	
	int updateNumber(Map<String,Date> map);
}
