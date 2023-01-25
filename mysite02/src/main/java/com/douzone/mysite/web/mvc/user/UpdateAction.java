package com.douzone.mysite.web.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.UserDao;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web2.mvc.Action;
import com.douzone.web2.util.MvcUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		session.setAttribute("authUser", authUser);
		if(authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
					return;
		}
		
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String password = request.getParameter("password");
		UserVo vo = new UserVo();
		vo.setName(name);
		vo.setNo(authUser.getNo());
		vo.setGender(gender);
		if(!password.equals("")) {
			vo.setPassword(password);
		}
		
		new UserDao().userpUpdate(vo);
		authUser.setName(name);
		
		MvcUtil.redirect(request.getContextPath() + "/user?a=updateform", request, response);
	}

}
