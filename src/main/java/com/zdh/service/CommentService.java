package com.zdh.service;


import java.util.List;

import com.zdh.bean.Page;
import com.zdh.dto.CommentDto;
import com.zdh.dto.CommentForSubmitDto;
import com.zdh.dto.CommentListDto;

public interface CommentService {
    
    /**
     * 保存评论
     * @param commentForSubmitDto 提交的评论DTO对象
     * @return 是否保存成功：true-成功，false-失败
     */
    boolean add(CommentForSubmitDto commentForSubmitDto);
    
    /**
     * 按分页条件，根据商户ID获取商户下的评论列表dto
     * @param businessId 商户ID
     * @param page 分页对象
     * @return 评论列表dto
     */
    CommentListDto getListByBusinessId(Long businessId,Page page);
    
    /**
     * 查询Comment对象
     * @return CommentDto对象
     */
    List<CommentDto> getCommentList();
    
}