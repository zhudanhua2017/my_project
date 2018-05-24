package com.zdh.dao;

import java.util.List;

import com.zdh.bean.Ad;

public interface AdDao {
	
	int insert(Ad ad);
	
	List<Ad> selectByPage(Ad ad);
	
	Ad selectById(Long id);
	
	int delete(Long id);
	
	int update(Ad ad);
}
