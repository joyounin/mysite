package com.douzone.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

public class SiteInterceptor implements HandlerInterceptor {
	@Autowired
	private SiteService siteService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//2023.02.08 강사님 코드
		SiteVo siteVo = (SiteVo)request.getServletContext().getAttribute("sitevo");
		if(siteVo == null) {
			siteVo = siteService.getSite();
			request.getServletContext().setAttribute("sitevo", siteVo);
		}
//		2023.02.07 내가 짠 코드
//		SiteVo site = siteService.getSite();
//		servletContext.setAttribute("site", site);
//		
		return true;
	}
	
}
