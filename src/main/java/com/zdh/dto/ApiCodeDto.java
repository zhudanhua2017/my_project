package com.zdh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zdh.constant.ApiCodeEnum;

@JsonInclude(Include.NON_NULL)
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

	public Integer getErrno() {
		return errno;
	}

	public void setErrno(Integer errno) {
		this.errno = errno;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
