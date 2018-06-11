package com.zdh.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdh.bean.Business;
import com.zdh.bean.Comment;
import com.zdh.bean.Orders;
import com.zdh.bean.Page;
import com.zdh.constant.CommentStateConst;
import com.zdh.dao.CommentDao;
import com.zdh.dao.MemberDao;
import com.zdh.dao.OrdersDao;
import com.zdh.dto.CommentDto;
import com.zdh.dto.CommentForSubmitDto;
import com.zdh.dto.CommentListDto;
import com.zdh.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Resource
	private CommentDao commentDao;
	
	@Resource
	private OrdersDao ordersDao;
	
	@Resource
	private MemberDao memberDao;

	@Override
	@Transactional  //开启事务
	//保存评论的同时要更新订单表里“评论状态”，这两步需要在一个事务里
	public boolean add(CommentForSubmitDto commentForSubmitDto) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentForSubmitDto, comment);
		comment.setId(null);
		comment.setOrdersId(commentForSubmitDto.getId());
		comment.setCreateTime(new Date());
		// 保存评论
		commentDao.insert(comment);
		Orders orders = new Orders();
		orders.setId(commentForSubmitDto.getId());
		orders.setCommentState(CommentStateConst.HAS_COMMENT);
		// 更新订单评论状态
		ordersDao.update(orders);
		return true;
	}

	@Override
	public CommentListDto getListByBusinessId(Long businessId, Page page) {
		CommentListDto result = new CommentListDto();
		
		// 组织查询条件
		Comment comment = new Comment();
		Orders orders = new Orders();
		Business business = new Business();
		// 评论里包含了订单对象
		comment.setOrders(orders);
		// 订单对象里包含了商户对象
		orders.setBusiness(business);
		// 设置商户主键
		business.setId(businessId);
		// 前端app页码从0开始计算，这里需要+1
		page.setCurrentPage(page.getCurrentPage() + 1);
		// 设置分页条件
		comment.setPage(page);
		List<Comment> commentList = commentDao.selectByPage(comment);
		
		// 组织返回值
		List<CommentDto> data = new ArrayList<>();
		result.setData(data);
		for(Comment commentTemp : commentList) {
			CommentDto commentDto = new CommentDto();
			data.add(commentDto);
			BeanUtils.copyProperties(commentTemp, commentDto);
			// 隐藏手机号中间4位
			StringBuffer phoneBuffer = new StringBuffer(String.valueOf(commentTemp.getOrders().getMember().getPhone()));
			commentDto.setUsername(phoneBuffer.replace(3, 7, "****").toString());
		}
		result.setHasMore(page.getCurrentPage() < page.getTotalPage());
		return result;
	}

	@Override
	public List<CommentDto> getCommentList() {
		// TODO Auto-generated method stub
		List<Comment> list = commentDao.select();
		List<CommentDto> result = new ArrayList<CommentDto> ();
		for(Comment comment:list){
			CommentDto tempDto = new CommentDto();
			BeanUtils.copyProperties(comment, tempDto);
			Orders orders = ordersDao.selectById(comment.getId());
			String phone = memberDao.getPhoneById(orders.getMemberId())+"";
			tempDto.setUsername(phone);
			result.add(tempDto);
		}
		return result;
	}

}
