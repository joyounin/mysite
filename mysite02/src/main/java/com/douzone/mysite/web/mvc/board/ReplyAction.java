package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVo authUser = (UserVo)request.getSession().getAttribute("authUser");
		if(authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		String sno = request.getParameter("no");
		Long no = Long.parseLong(sno);
		BoardVo vo = new BoardDao().findByNo(no);
		
		vo.setTitle(request.getParameter("title"));
		vo.setContents(request.getParameter("content"));
		vo.setHit(vo.getHit());
		vo.setUserno(authUser.getNo());
		vo.setOrderno(vo.getOrderno());
		vo.setDepth(vo.getDepth()+1);
		
//		System.out.println(vo);
				
		new BoardDao().replyupdate(vo);
		new BoardDao().replyinsert(vo);
		
		MvcUtil.redirect(request.getContextPath() + "/board?a=list",request, response);

	}

}
