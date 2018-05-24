package com.zdh.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Setter @Getter
public class Orders {
	private Long id;
	private Long memberId;
	private Long businessId;
	private Integer num;
	private Integer commentState;
	private Double price;
	private Date createTime;
	private Business business;
	private Member member;
}