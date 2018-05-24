package com.zdh.bean;

import lombok.*;

/**
 * 分页对象
 */
@Setter @Getter
public class Page {
	
	// 总条数
	private int totalNumber;
	// 当前页数
	private int currentPage;
	// 总页数
	private int totalPage;
	// 每页显示条数
	private int pageNumber;
	
	public Page() {
	    this.currentPage = 1;
	    this.pageNumber = 5;
	}

	public void count() {
		this.totalPage = this.totalNumber / this.pageNumber;
		if(this.totalNumber % this.pageNumber > 0) {
			this.totalPage++;
		}
		if(this.totalPage <= 0) {
			this.totalPage = 1;
		}
		if(this.currentPage > this.totalPage) {
			this.currentPage = this.totalPage;
		}
		if(this.currentPage <= 0) {
			this.currentPage = 1;
		}
	}
	
}