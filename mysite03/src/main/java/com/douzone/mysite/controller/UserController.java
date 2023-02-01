package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpSession session, UserVo vo, Model model) {
		UserVo authUser = userService.getUser(vo);
		
		if(authUser == null) {
			model.addAttribute("email", vo.getEmail());
			return "user/login";
		}
		
		session.setAttribute("authUser", authUser);
		return "redirect:/";
	}
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	// 2023.01.31에 짠 코드
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		// Access Control authUser가 아닐경우 주소창으로 들어오는 방법을 막는다
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		/////////////////////////////////////////////////
		
		UserVo userVo = userService.getUser(authUser.getNo());
		
		model.addAttribute("UserVo", userVo);
		return "user/update";
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(HttpSession session, UserVo vo) {
		// Access Control authUser가 아닐경우 주소창으로 들어오는 방법을 막는다
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		/////////////////////////////////////////////////
		
		vo.setNo(authUser.getNo());
		userService.updateUser(vo);
		
		authUser.setName(vo.getName());
		
		return "redirect:/";
	}
	
	// 2023.01.30에 짠 코드
//	 update라는 url이 들어오면 현재 로그인된 유저정보를 찾는다
//	@RequestMapping(value="/update", method=RequestMethod.GET)
//	public String update(HttpSession session, Model model) {
//		UserVo authUser = (UserVo) session.getAttribute("authUser");
//		UserVo vo = userService.findByno(authUser.getNo());
//		// view에 vo라는 이름으로 뿌려준다.
//		model.addAttribute("vo", vo);
//		
//		return "user/update";
//	}
//	
//	@RequestMapping(value="/update", method=RequestMethod.POST)
//	public String update(HttpSession session, UserVo vo) {
//		userService.update(session, vo);
//		
//		return "redirect:/";
//	}
}
