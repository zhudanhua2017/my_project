package com.zdh.bean;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BaseBean {
	
	private Page page;
	
	public BaseBean() {
	    this.page = new Page();
	}
	
}