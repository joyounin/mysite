package com.douzone.mysite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String index(@RequestParam("page") int page, Model model, @RequestParam("kwd") String kwd) {
		Map<String, Object> map = boardService.getContentsList(page, kwd);
		
		// allattributes 사용해보기
		// model.addAllAttributes(map);
		model.addAttribute("map", map);
		
		return "board/list";
	}
	
//	@RequestMapping("/write")
//	public String write(BoardVo vo) {
//		boardService.write(vo);
//		
//		return "redirect:/board/list";
//	}
	
	
	
	
}
