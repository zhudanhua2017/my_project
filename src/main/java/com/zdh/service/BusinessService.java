package com.zdh.service;

import java.util.List;

import com.zdh.dto.BusinessDto;
import com.zdh.dto.BusinessListDto;

public interface BusinessService {
	/**
     * 分页搜索商户列表
     * @param dto 查询条件(包含分页对象)
     * @return 商户列表
     */
	List<BusinessDto> selectByPage(BusinessDto dto);
	
	/**
	 * 删除
	 * @param id
	 * @return true:删除成功 false：删除失败
	 */
	boolean remove(Long id);
	
	/**
	 * 修改
	 * @param dto
	 * @return true:修改成功 false：修改失败
	 */
	boolean modify(BusinessDto dto);
	/**
	 * 新增
	 * @param dto 商户dto对象
	 * @return 是否新增成功：true-成功;fale-失败
	 */
	boolean add(BusinessDto dto);
	/**
     * 根据主键获取商户dto
     * @param id 主键
     * @return 商户dto
     */
	BusinessDto getById(Long id);
	/**
     * 分页搜索商户列表(接口专用)
     * @param businessDto 查询条件(包含分页对象)
     * @return 商户列表Dto对象
     */
	BusinessListDto searchByPageForApi(BusinessDto businessDto);
}
