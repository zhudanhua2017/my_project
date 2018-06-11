package com.zdh.dto;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zdh.constant.ApiCodeEnum;

@JsonInclude(Include.NON_NULL)
@Getter @Setter
public class ApiCodeDto {

	private Integer errno;//存放ApiCodeEnum.java中的状态码

	private String msg;  //存放ApiCodeEnum.java中的状态信息

	private String token;

	private String code; //存放6位随机码

	public ApiCodeDto() {

	}

	public ApiCodeDto(Integer errno, String code) {
		this.errno = errno;
		this.code = code;
	}

	public ApiCodeDto(ApiCodeEnum apiCodeEnum) {
		this.errno = apiCodeEnum.getErrno();
		this.msg = apiCodeEnum.getMsg();
	}

}
