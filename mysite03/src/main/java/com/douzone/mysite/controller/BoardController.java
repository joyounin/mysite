package com.douzone.mysite.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String index(@RequestParam(value = "page", defaultValue = "1", required = true) int page,
		@RequestParam(value="keyword", defaultValue = "", required = true) String keyword, Model model) {
		System.out.println(keyword);
		Map<String, Object> map = boardService.getContentsList(page, keyword);

		// allattributes 사용해보기
		model.addAllAttributes(map);

		return "board/list";
	}

	@RequestMapping("/view")
	public String view(Model model, HttpServletRequest request, HttpServletResponse response) {
		String sno = request.getParameter("no");
		Long no = Long.parseLong(sno);

		BoardVo boardvo = boardService.getContents(no);

		model.addAttribute("boardvo", boardvo);

		boolean bcookie = false;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (sno.equals(cookie.getName())) {
					bcookie = true;
				}
			}
		}
		if (!bcookie) {
			// 쿠키 쓰기
			Cookie cookie2 = new Cookie(sno, String.valueOf(1));
			cookie2.setPath(request.getContextPath());
			cookie2.setMaxAge(24 * 60 * 60); // 1day
			response.addCookie(cookie2);
			boardService.hit(no);
		}
		return "board/view";
	}
	
	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@AuthUser UserVo authUser, @RequestParam("no") Long no, Model model) {
		BoardVo vo = boardService.getContents(no, authUser.getNo());
		model.addAttribute("boardvo", vo);

		return "board/modify";
	}
	
	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@AuthUser UserVo authUser, Model model, BoardVo boardvo) {
		boardvo.setUserno(authUser.getNo());

		boardService.modify(boardvo);
		model.addAttribute("boardvo", boardvo);
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, Model model, BoardVo boardvo) {
		boardvo.setUserno(authUser.getNo());
		
		boardService.write(boardvo);
		model.addAttribute("boardvo", boardvo);
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping("/delete")
	public String delete(@AuthUser UserVo authUser, @RequestParam("no") Long no, Model model, BoardVo boardvo) {
		boardvo.setUserno(authUser.getNo());
		boardService.deleteContents(no, authUser.getNo());
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public String reply(@AuthUser UserVo authUser, @RequestParam("no") Long no, Model model,BoardVo boardvo) {
		boardvo.setUserno(authUser.getNo());
		
		boardvo = boardService.getContents(no);
		model.addAttribute("boardvo", boardvo);
		return "board/reply";
	}

	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply( BoardVo boardvo) {
		boardService.replyupdateContents(boardvo);
		boardService.replyInsert(boardvo);
		return "redirect:/board";
	}

}