package com.zdh.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Getter @Setter
public class BusinessListDto {
	
	private boolean hasMore;
	private List<BusinessDto> data;
	
	public BusinessListDto() {
	    this.data = new ArrayList<BusinessDto>();
	}
}
