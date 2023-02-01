package com.douzone.mysite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list")
	public String index(Model model) {
		Map<String, Object> map = boardService.getContentsList(1, "");
		
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
