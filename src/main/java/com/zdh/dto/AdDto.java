package com.zdh.dto;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zdh.bean.Ad;

@Getter @Setter
@JsonInclude(Include.NON_NULL)
public class AdDto extends Ad{
	
	private String img;
    private MultipartFile imgFile;
    
}
