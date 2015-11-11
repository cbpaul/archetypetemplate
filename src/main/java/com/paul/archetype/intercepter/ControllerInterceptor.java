package com.paul.archetype.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.paul.archetype.web.Constants;


/**
 * @包名   
 * @文件名 ControllerInterceptor.java
 * @作者   paul
 * @创建日期 2014-2-11
 * @版本 V 1.0
 */
public class ControllerInterceptor implements HandlerInterceptor {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/**
		 * 后台用户是否在线
		 */
		Long sessionId=(Long)request.getSession().getAttribute(Constants.SESSION_UID);
		if(sessionId==null){
			String ajaxStr=request.getHeader("X-Requested-With");
			if("XMLHttpRequest".equals(ajaxStr)){
				response.getWriter().write("");
			}else{
				response.sendRedirect(request.getSession().getServletContext().getContextPath());
			}
			return false;
		}
		/**
		 * 后台权限控制
		 */
//		String servletPath=request.getServletPath();
//		servletPath = servletPath.substring(1);
//		long roleId=(Long)request.getSession().getAttribute(Constants.USER_ROLE_ID);
//		if(roleId!=1){
//			Set<ManagerAuthority> authorities=Constants.roleAuthMap.get(roleId);
//			try {
//				if(authorities==null){
//					response.sendRedirect(request.getSession().getServletContext().getContextPath());
//					return false;
//				}
//				if(!authorities.contains(new ManagerAuthority(servletPath))){
//					return false;
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		
//		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			{

	}

}
