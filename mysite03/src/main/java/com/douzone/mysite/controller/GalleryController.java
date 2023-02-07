package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.GallerySerivice;
import com.douzone.mysite.vo.GalleryVo;


@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private FileUploadService fileuploadService;
	
	@Autowired
	private GallerySerivice galleryService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.getImages();
		model.addAttribute("list",list);
		
		return "gallery/index";
	}
	
	@Auth(role="ADMIN")
	@RequestMapping("/upload")
	public String upload(GalleryVo vo, 
			@RequestParam("file") MultipartFile file, 
			@RequestParam("comments") String comments,
			Model model) {
		String url = fileuploadService.restore(file);
		vo.setUrl(url);
		vo.setComments(comments);
		galleryService.addImage(vo);
		
		return "redirect:/gallery";
	}
	
	@Auth(role="ADMIN")
	@RequestMapping("/delete")
	public String delete(@RequestParam("no") Long no) {
		galleryService.removeImage(no);
		return "redirect:/gallery";
	}
}
