package com.zdh.dto;


import lombok.Getter;
import lombok.Setter;

import com.zdh.bean.Comment;

@Setter @Getter
public class CommentDto extends Comment {
    
    /**
     * 隐藏中间4位的手机号
     */
    private String username;

}