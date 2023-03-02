package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@RestController("guestbookApiController")
@RequestMapping("/guestbook/api")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	// 3개의 메소드 핸들러
	// 글쓰기(GET), 글삭제(DELETE), 리스트(GET)
	
	// list
	@GetMapping("")
	public JsonResult list(@RequestParam(value="sno", required=true, defaultValue="0") Long startNo) {
		List<GuestbookVo> list = guestbookService.getMessageList(startNo);
		System.out.println(list);
		return JsonResult.success(list);
	}
	
	// add
	@PostMapping("")
	public JsonResult insert(@RequestBody GuestbookVo vo) {
		guestbookService.addMessage(vo);
		
		return JsonResult.success(vo);
	}
//
	// delete
	@DeleteMapping("/{no}")
	public JsonResult delete(@PathVariable("no") Long no, @RequestParam(value="password", required=true, defaultValue="") String password) {
		guestbookService.deleteMessage(no, password);
		return JsonResult.success(no);
	}
	
	
}
