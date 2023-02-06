package com.douzone.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result,Model model) {
		if(result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();
//			for(ObjectError error : list) {
//				System.out.println(error);
//			}
//			model.addAttribute("userVo", vo);
			
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		
		//userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	@RequestMapping(value="/login")
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/logout")
	public String logout() {
		return "redirect:/";
	}
	
	// 2023.01.31에 짠 코드
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
	
		UserVo userVo = userService.getUser(authUser.getNo());
		
		model.addAttribute("UserVo", userVo);
		return "user/update";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, UserVo vo) {
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
