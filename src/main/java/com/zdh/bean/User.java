package com.zdh.bean;

import lombok.Getter;
import lombok.Setter;
/**
 * 用户
 * @author Administrator
 */
@Setter @Getter
public class User {
	
	private Long id;
	private String name;
	private String password;
	private String chName;
	private Long groupId;

}