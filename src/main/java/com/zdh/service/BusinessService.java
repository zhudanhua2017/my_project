package com.zdh.service;

import java.util.List;

import com.zdh.dto.BusinessDto;
import com.zdh.dto.BusinessListDto;

public interface BusinessService {
	
	List<BusinessDto> selectByPage(BusinessDto dto);
	
	boolean remove(Long id);
	
	boolean modify(BusinessDto dto);
	
	boolean add(BusinessDto dto);
	
	BusinessDto getById(Long id);
	
	BusinessListDto searchByPageForApi(BusinessDto businessDto);
}
