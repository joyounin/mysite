package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String suserno = request.getParameter("userno");
		Long userno = Long.parseLong(suserno);
		
		String sno = request.getParameter("no");
		Long no = Long.parseLong(sno);
		
		new BoardDao().deletebyno(userno, no);
		MvcUtil.redirect(request.getContextPath() + "/board?a=list",request, response);

	}

}
