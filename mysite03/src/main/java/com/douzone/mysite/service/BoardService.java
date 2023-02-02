package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	private static final int LIST_SIZE = 2; // 리스팅되는 게시물의 수
	private static final int PAGE_SIZE = 5; // 페이지 리스트의 수
	
	@Autowired
	private BoardRepository boardRepository;

	public void addContents(BoardVo vo) {
		
	}
	
	// view에서 요청하는 서비스
	public BoardVo getContents(Long no) {
		return boardRepository.findByNo(no);
	}
	
	// 게시글 수정할때 폼
	public BoardVo getContents(Long no, Long userno) {
		return boardRepository.findByNoAndUserNo(no, userno);
	}
	
	// 업데이트
	public void updateContents(BoardVo vo) {
		
	}
	
	// 삭제
	public void deleteContents(Long no, Long UserNo) {
		
	}
	
	// 페이지 검색 기능
	public Map<String, Object> getContentsList(int page, String keyword) {
		int toTalCount = boardRepository.getTotalCount(keyword);

		
		// 1. view에서 게시판 리스트를 렌더링 하기 위한 데이터 값 계산
		// 시작 페이지
		int startPage = page;
		// 처음 리스트 페이지 1~5 6~10
		int endPage = (int)Math.ceil(page/(double)PAGE_SIZE) * PAGE_SIZE;
		
		int beginPage = endPage - (PAGE_SIZE - 1);
		// 끝페이지
		int totalPage = (int)Math.ceil(toTalCount/(double)LIST_SIZE);
		
		
		// 2. 리스트 가져오기
		List<BoardVo> list = boardRepository.findAllByPageAndKeyWord(page, keyword, LIST_SIZE);
		
		// 3. 리스트 정보를 맵에 저장
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("startPage", startPage);
		map.put("beginPage", beginPage);
		map.put("endPage", endPage);
		map.put("totalPage", totalPage);
		map.put("keyword", keyword);
		
		return map;
	}

	public void write(BoardVo vo) {
		boardRepository.write(vo);
		
	}

	
	
}
