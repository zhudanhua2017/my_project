package com.zdh.service;

import java.util.List;

import com.zdh.dto.AdDto;

public interface AdService {
	/**
	 * 新增动作
	 * @param dto
	 * @return true:新增成功;false:新增失败
	 */
	boolean add(AdDto adDto);
	
	/**
	 * 查询
	 * @param adDto
	 * @return AdDto对象列表
	 */
	List<AdDto> searchByPage(AdDto adDto);
	/**
	 * 删除动作
	 * @param id
	 * @return true:删除成功;false:删除失败
	 */
	boolean remove(Long id);
	/**
	 * 通过id获取动作
	 * @param id
	 * @return 动作DTO对象
	 */
	AdDto getById(Long id);
	/**
	 * 修改动作
	 * @param dto
	 * @return true:修改成功;false:修改失败
	 */
	boolean modify(AdDto adDto);
	
	
}
