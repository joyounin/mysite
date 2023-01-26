package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String useno = request.getParameter("userno");
		Long userno = Long.parseLong(useno);
		String sno = request.getParameter("no");
		Long no = Long.parseLong(sno);
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(content);
		vo.setUserno(userno);
		vo.setNo(no);

		new BoardDao().modify(vo);
		
		MvcUtil.redirect(request.getContextPath() + "/board?a=list",request, response);

	}

}
