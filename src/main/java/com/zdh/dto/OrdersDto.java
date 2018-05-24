package com.zdh.dto;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.zdh.bean.Orders;

@JsonInclude(Include.NON_NULL)
@Setter @Getter
public class OrdersDto extends Orders {
    private String img;
    private String title;
    private Integer count;
    
}