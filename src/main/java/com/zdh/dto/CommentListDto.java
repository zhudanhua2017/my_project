package com.zdh.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Setter @Getter
public class CommentListDto {
	
	private boolean hasMore;
	private List<CommentDto> data;
	
	
}