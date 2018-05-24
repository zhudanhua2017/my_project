package com.zdh.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zdh.constant.SessionKeyConst;


/**
 * session拦截器
 * @author Administrator
 *
 */
public class SessionInterceptor implements HandlerInterceptor{

	/**
	 * 进入Handler方法之前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		if (request.getSession().getAttribute(SessionKeyConst.USER_INFO) != null) {
			return true;
		}
		// 针对ajax请求处理
	/*	if (request.getHeader("x-requested-with") != null) {
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			response.setHeader("url", basePath + "/login/sessionTimeout");
		} else */
			request.getRequestDispatcher("/login/sessionTimeout").forward(request, response);
		
		return false;
	}

	/**
	 * 进入Handler方法之后，返回ModelAndView之前执行
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 在Handler方法执行完后执行
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
