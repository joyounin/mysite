package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p = request.getParameter("page");
		int firstnum = 1;
		int lastnum = 5;
		if(p != null) {
			firstnum = Integer.parseInt(p);
		}
		
		List<BoardVo> list = new BoardDao().findlist(firstnum, lastnum);
		request.setAttribute("list", list);
		request.setAttribute("firstnum", firstnum);
//		System.out.println(firstnum);
		int totalcount = new BoardDao().findcount(lastnum);
		
//		System.out.println(totalcount);
		request.setAttribute("totalcount", totalcount);
		
		
		MvcUtil.forward("board/list", request, response);

	}

}
