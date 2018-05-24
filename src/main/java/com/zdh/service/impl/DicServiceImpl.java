package com.zdh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdh.bean.Dic;
import com.zdh.dao.DicDao;
import com.zdh.service.DicService;
@Service
public class DicServiceImpl implements DicService{

	@Autowired
    private DicDao dicDao;
	
	@Override
	public List<Dic> getListByType(String type) {
		// TODO Auto-generated method stub
		Dic dic = new Dic();
		dic.setType(type);
		return dicDao.select(dic);
	}

}
