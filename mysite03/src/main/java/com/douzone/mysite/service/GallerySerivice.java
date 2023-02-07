package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GalleryRepository;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GallerySerivice {
	
	@Autowired
	private GalleryRepository galleryRepositroy;
	
	public List<GalleryVo> getImages(){
		return galleryRepositroy.findAll();
	}
	
	public void removeImage(Long no) {
		galleryRepositroy.delete(no);
	}
	
	public void addImage(GalleryVo vo) {
		galleryRepositroy.upload(vo);
	}
	
}
