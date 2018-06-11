package com.zdh.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zdh.bean.Ad;
import com.zdh.dao.AdDao;
import com.zdh.dto.AdDto;
import com.zdh.service.AdService;
import com.zdh.util.FileUtil;
@Service
public class AdServiceImpl implements AdService{
	
	@Autowired
	private AdDao adDao;
	
	@Value("${adImageSavePath}")
	private String adImageSavePath; //图片保存路径
	
	@Value("${adImageUrl}")
	private String adImageUrl; //访问图片地址
	
	@Override
	public boolean add(AdDto adDto){
		Ad ad = new Ad();
		BeanUtils.copyProperties(adDto,ad);
		if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
			
			String fileName = System.currentTimeMillis() + "_" + adDto.getImgFile().getOriginalFilename();
            
            File file = new File(adImageSavePath + fileName);
 			File fileFolder = new File(adImageSavePath);
             
			if (!fileFolder.exists()) {
				fileFolder.mkdirs(); //创建文件夹
			}
			try {
				adDto.getImgFile().transferTo(file); //图片存储的位置
				ad.setImgFileName(fileName);
				adDao.insert(ad);
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public List<AdDto> searchByPage(AdDto adDto) {
		// TODO Auto-generated method stub
		List<AdDto> result = new ArrayList<AdDto>();
		Ad condition = new Ad();
		BeanUtils.copyProperties(adDto, condition);  //属性名相同就copy
		List<Ad> adList = adDao.selectByPage(condition);
		for(Ad ad:adList){
			AdDto adDtoTemp = new AdDto();
			BeanUtils.copyProperties(ad,adDtoTemp);
			adDtoTemp.setImg(adImageUrl + ad.getImgFileName());
			result.add(adDtoTemp);
		}
		return result;
	}

	@Override
	public boolean remove(Long id) {
		// TODO Auto-generated method stub
		Ad ad = adDao.selectById(id);
		int deleteRows = adDao.delete(id);
		FileUtil.delete(adImageSavePath + ad.getImgFileName()); //删除上传的图片
		return deleteRows == 1;
		
	}
	
	@Override
	public AdDto getById(Long id){
		Ad ad = adDao.selectById(id);
		AdDto adDto = new AdDto();
		BeanUtils.copyProperties(ad, adDto);
		adDto.setImg(adImageUrl+ad.getImgFileName());
		return adDto;
	}
	
	@Override
	public boolean modify(AdDto adDto){
		Ad ad = new Ad();
		BeanUtils.copyProperties(adDto, ad);
		String fileName = null;
		if(adDto.getImgFile()!=null && adDto.getImgFile().getSize() > 0){
			try{
				fileName = FileUtil.save(adDto.getImgFile(), adImageSavePath);
				ad.setImgFileName(fileName);
			}catch (Exception e) {
				
				return false;
			}
		}
		int updateCount = adDao.update(ad);
		if (updateCount != 1) {
			return false;
		}
		if (fileName != null) {
			return FileUtil.delete(adImageSavePath + adDto.getImgFileName());
		}
		return true;
	}

}
