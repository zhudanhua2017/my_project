<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
		<title></title>
		<link rel="stylesheet" type="text/css" href="http://127.0.0.1:8081/comment/css/all.css"/>
		<link rel="stylesheet" type="text/css" href="http://127.0.0.1:8081/comment/css/pop.css"/>
		<link rel="stylesheet" type="text/css" href="http://127.0.0.1:8081/comment/css/main.css"/>
		<script type="text/javascript" src="http://127.0.0.1:8081/comment/js/common/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="http://127.0.0.1:8081/comment/js/content/commentList.js"></script>
	</head>
	<body style="background: #e1e9eb;">
		<form action="http://127.0.0.1:8081/comment/ad/search" id="mainForm" method="post">
			<input id="id" name="id" type="hidden" />
			<input id="basePath" value="http://127.0.0.1:8081/comment" type="hidden" />
			<input name="page.currentPage" id="currentPage" value="1" type="hidden" />
			<div class="right">
				<div class="current">当前位置：<a href="#">内容管理</a> &gt; 评论查询</div>
				<div class="rightCont">
					<p class="g_title fix">评论列表</p>
					<div class="zixun fix">
						<table class="tab2" width="100%">
							<tbody>
								<tr>
								    <th>序号</th>
								    <th>订单号</th>
								    <th>评论星级</th>
								    <th>手机号</th>
								</tr>
								<c:forEach items="${commentList}" var="item" varStatus="s">
									<tr>
										<td>${s.index + 1 }</td>
										<td>${item.orders.id}</td>
										<td>${item.star }</td>
										<td>${item.username }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<!-- 分页 -->
						<t:page jsMethodName="search" page="${searchParam.page}"></t:page>
					</div>
				</div>
			</div>
		</form>
</body></html>