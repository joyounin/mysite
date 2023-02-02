package com.douzone.mysite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("")
	public String index(@RequestParam(value="page", defaultValue = "1", required = false) int page, @RequestParam(value="keyword", defaultValue = "", required = false) String keyword, Model model) {
		Map<String, Object> map = boardService.getContentsList(page, keyword);
		
		// allattributes 사용해보기
		model.addAllAttributes(map);
		// model.addAttribute("map", map);
		
		return "board/list";
	}
	
	@RequestMapping("/view")
	public String view(@RequestParam("no") Long no, Model model) {
		BoardVo boardvo = boardService.getContents(no);
		System.out.println(boardvo.getUserno());
		model.addAttribute("boardvo", boardvo);
		return "board/view";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@RequestParam("userno") Long userno, @RequestParam("no") Long no, Model model) {
		BoardVo vo = boardService.getContents(no, userno);
		model.addAttribute("boardvo", vo);
		
		return "board/modify";
	}
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String modify() {
		
		return "board/modify";
	}
	
//	@RequestMapping("/write")
//	public String write(BoardVo vo) {
//		boardService.write(vo);
//		
//		return "redirect:/board/write";
//	}
	
	
	
	
}
