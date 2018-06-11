package com.zdh.dao;

import java.util.List;

import com.zdh.bean.Member;

public interface MemberDao {
    /**
     * 根据查询条件查询会员列表
     * @param member 查询条件
     * @return 会员列表
     */
    List<Member> select(Member member);
    
    /**
     * 根据会员id得到会员手机号
     * @param 会员id
     * @return 会员手机号
     */
    Long getPhoneById(Long memberId);
}