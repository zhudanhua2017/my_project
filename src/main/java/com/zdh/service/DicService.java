package com.zdh.service;

import java.util.List;

import com.zdh.bean.Dic;

public interface DicService {
	
	List<Dic> getListByType(String type);

}
