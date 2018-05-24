package com.zdh.bean;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Getter @Setter
public class Ad extends BaseBean{
    private Long id;
    private String title;
    private String imgFileName; //数据库中的图片名称
    private String link;
    private Long weight;
  
}