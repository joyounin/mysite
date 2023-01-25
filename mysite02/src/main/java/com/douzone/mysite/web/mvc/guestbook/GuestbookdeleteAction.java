package com.douzone.mysite.web.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDao;
import com.douzone.web2.mvc.Action;
import com.douzone.web2.util.MvcUtil;

public class GuestbookdeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sno = request.getParameter("no");
		Long no = Long.parseLong(sno);
		String password = request.getParameter("password");
		
		new GuestbookDao().deleteByPassword(no, password);
		MvcUtil.redirect(request.getContextPath() + "/guestbook?a=list",request, response);

	}

}
