package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class NewBookAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String shit = request.getParameter("hit");
		Integer hit = Integer.parseInt(shit);
		String sg = request.getParameter("groupno");
		Integer groupno = Integer.parseInt(sg);
		String so = request.getParameter("orderno");
		Integer orderno = Integer.parseInt(so);
		String sd = request.getParameter("depth");
		Integer depth = Integer.parseInt(sd);
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(content);
		vo.setHit(hit);
		
		new BoardDao().insert(vo);
		
		MvcUtil.redirect(request.getContextPath() + "/board?a=list",request, response);
	}

}
