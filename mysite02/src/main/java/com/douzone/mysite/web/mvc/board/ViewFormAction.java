package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sno = request.getParameter("no");
		Long no = Long.parseLong(sno);
		
		BoardVo vo = new BoardDao().findByNo(no);
		request.setAttribute("vo", vo);
		
		boolean bcookie = false;
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(sno.equals(cookie.getName())){
					bcookie = true;
				}
			}
		}
		if(!bcookie) {
			// 쿠키 쓰기
			Cookie cookie2 = new Cookie(sno, String.valueOf(1));
			cookie2.setPath(request.getContextPath());
			cookie2.setMaxAge(24 * 60 * 60);  // 1day
			response.addCookie(cookie2);
			BoardVo vo1 = new BoardDao().hit(no);
			request.setAttribute("vo1", vo1);
		}
		
		
		MvcUtil.forward("board/view", request, response);	

	}

}
