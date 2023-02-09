package com.douzone.mysite.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

public class SiteInterceptor implements HandlerInterceptor {
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private SiteService siteService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//2023.02.08 강사님 코드
//		SiteVo site = (SiteVo)request.getServletContext().getAttribute("siteVo");
//		if(site == null) {
//			siteService.getSite();
//			request.getServletContext().setAttribute("site", site);
//		}
//		2023.02.07 내가 짠 코드
		SiteVo site = siteService.getSite();
		servletContext.setAttribute("site", site);
		
		return true;
	}
	
}
