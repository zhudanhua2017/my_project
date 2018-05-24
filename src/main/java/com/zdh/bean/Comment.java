package com.zdh.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Setter @Getter
public class Comment extends BaseBean {
	
	private Long id;
	private String comment;
	private Integer star;
	private Long ordersId;
	private Date createTime;
	private Orders orders;
	
}
