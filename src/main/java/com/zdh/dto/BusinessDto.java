package com.zdh.dto;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zdh.bean.Business;

@JsonInclude(Include.NON_NULL)
@Getter @Setter
public class BusinessDto extends Business{
    
    private String img;
    private MultipartFile imgFile;
    private String keyword;
    private Integer mumber;
    private Integer star;
    
}