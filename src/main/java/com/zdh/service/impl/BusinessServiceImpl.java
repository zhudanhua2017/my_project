package com.zdh.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zdh.bean.Business;
import com.zdh.bean.Page;
import com.zdh.constant.CategoryConst;
import com.zdh.dao.BusinessDao;
import com.zdh.dto.BusinessDto;
import com.zdh.dto.BusinessListDto;
import com.zdh.service.BusinessService;
import com.zdh.util.CommonUtil;
import com.zdh.util.FileUtil;
@Service
public class BusinessServiceImpl implements BusinessService{

	@Autowired
	private BusinessDao businessdao;
	
	@Value("${businessImage.savePath}")
	private String savePath;

	@Value("${businessImage.url}")
	private String url;
	
	@Override
	public List<BusinessDto> selectByPage(BusinessDto dto) {
		// TODO Auto-generated method stub
		Business business = new Business();
		BeanUtils.copyProperties(dto, business);
		List<Business> list = businessdao.selectByPage(business);
		List<BusinessDto> result = new ArrayList<BusinessDto>();
		for(Business businessTemp:list){
			BusinessDto businessDtoTemp = new BusinessDto();
			BeanUtils.copyProperties(businessTemp, businessDtoTemp);
			businessDtoTemp.setImg(url+businessTemp.getImgFileName());
			result.add(businessDtoTemp);
		}
		
		return result;
	}

	@Override
	public boolean remove(Long id) {
		// TODO Auto-generated method stub
		Business business = businessdao.selectById(id);
		int deleteRows = businessdao.delete(id);
		FileUtil.delete(savePath+business.getImgFileName());
		return deleteRows == 1;
	}

	@Override
	public boolean add(BusinessDto dto) {
		// TODO Auto-generated method stub
		Business business = new Business();
		BeanUtils.copyProperties(dto, business);
		
		if (dto.getImgFile() != null && dto.getImgFile().getSize() > 0) {
			
			String fileName = System.currentTimeMillis() + "_" + dto.getImgFile().getOriginalFilename();
            
            File file = new File(savePath + fileName);
 			File fileFolder = new File(savePath);
             
			if (!fileFolder.exists()) {
				fileFolder.mkdirs(); //创建文件夹
			}
			try {
				dto.getImgFile().transferTo(file); //图片存储的位置
				business.setImgFileName(fileName);
				businessdao.insert(business);
				return true;
			} catch (Exception e) {
				// TODO 需要添加日志
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public BusinessDto getById(Long id) {
		// TODO Auto-generated method stub
		Business business = businessdao.selectById(id);
		BusinessDto dto = new BusinessDto();
		BeanUtils.copyProperties(business, dto);
		dto.setImg(url+business.getImgFileName());
		
		return dto;
	}

	@Override
	public boolean modify(BusinessDto dto) {
		// TODO Auto-generated method stub
		Business business = new Business();
		BeanUtils.copyProperties(dto, business);
		String fileName = null;
		if(dto.getImgFile()!=null && dto.getImgFile().getSize() > 0){
			try{
				fileName = FileUtil.save(dto.getImgFile(), savePath);
				business.setImgFileName(fileName);
			}catch (Exception e) {
				// TODO 需要添加日志
				return false;
			}
		}
		int updateCount = businessdao.update(business);
		if (updateCount != 1) {
			return false;
		}
		if (fileName != null) {
			return FileUtil.delete(savePath + dto.getImgFileName());
		}
		return true;
	}

	@Override
	public BusinessListDto searchByPageForApi(BusinessDto businessDto) {
		BusinessListDto result = new BusinessListDto();

		// 组织查询条件
		Business businessForSelect = new Business();
		BeanUtils.copyProperties(businessDto, businessForSelect);
		// 当关键字不为空时，把关键字的值分别设置到标题、副标题、描述中
		// TODO 改进做法：全文检索
		if (!CommonUtil.isEmpty(businessDto.getKeyword())) {
			businessForSelect.setTitle(businessDto.getKeyword());
			businessForSelect.setSubtitle(businessDto.getKeyword());
			businessForSelect.setDesc(businessDto.getKeyword());
		}
		// 当类别为全部(all)时，需要将类别清空，不作为过滤条件
		if (businessDto.getCategory() != null && CategoryConst.ALL.equals(businessDto.getCategory())) {
			businessForSelect.setCategory(null);
		}
		// 前端app页码从0开始计算，这里需要+1
		int currentPage = businessForSelect.getPage().getCurrentPage();
		businessForSelect.getPage().setCurrentPage(currentPage + 1);
		List<Business> list = businessdao.selectLikeByPage(businessForSelect);
		// 经过查询后根据page对象设置hasMore
		Page page = businessForSelect.getPage();
		boolean flag = page.getCurrentPage() < page.getTotalPage();
		result.setHasMore(flag);

		// 对查询出的结果进行格式化
		for (Business business : list) {
			BusinessDto businessDtoTemp = new BusinessDto();
			result.getData().add(businessDtoTemp);
			BeanUtils.copyProperties(business, businessDtoTemp);
			businessDtoTemp.setImg(url + business.getImgFileName());
			// 为兼容前端mumber这个属性
			businessDtoTemp.setMumber(business.getNumber());
			businessDtoTemp.setStar(this.getStar(business));
		}

		return result;
	}

	private int getStar(Business business) {
		if(business.getStarTotalNum() != null && business.getCommentTotalNum() != null && business.getCommentTotalNum() != 0) {
			return (int)(business.getStarTotalNum() / business.getCommentTotalNum());
		} else {
			return 0;
		}
	}
}
