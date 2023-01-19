package com.douzone.mysite.web.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.UserDao;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Access Control(접근 제어)
		HttpSession session = request.getSession();
		if(session == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		////////////////////////////
		
		// 구현 하기
		// UserVo vo = new UserDao().findByNo(authUser.getNo());
		// where session 에 있는 값 동시에 바꾸기
		// password 있으면 이름,성별,비밀번호 변경, 없으면 이름,성별만 변경 dao에 업데이트 2개 만들기
		// 업데이트폼으로 리다이렉트로 보내기
		// 이름 이메일 성별 vo에 넣기
		request.setAttribute("vo", vo);
		MvcUtil.forward("user/updateform", request, response);
	}

}
