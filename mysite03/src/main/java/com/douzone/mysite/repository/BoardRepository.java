package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> findAllByPageAndKeyWord(int page, String keyword, int size) {
		Map<String, Object> map = new HashMap<>();
		map.put("startOffset", (page-1) * size);
		map.put("size", size);
		map.put("keyword", keyword);
		
		return sqlSession.selectList("board.findAllByPageAndKeyWord", map);
	}

	public int getTotalCount(String keyword) {
		return sqlSession.selectOne("board.getTotalCount", keyword);
	}

	public BoardVo findByNoAndUserNo(Long no, Long userno) {
		Map<String, Object> map = new HashMap<>();
		map.put("userno", userno);
		map.put("no", no);
		return sqlSession.selectOne("board.findByNoAndUserNo", map);
	}
	
	public BoardVo findByNo(Long no) {
		return sqlSession.selectOne("board.findByNo", no);
	}
	
			
	public void write(BoardVo boardvo) {
		sqlSession.insert("board.write", boardvo);
	}
	
	public void deleteByNoAndUserNo(Long no, Long userno) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("userno", userno);
		
		sqlSession.delete("board.deleteByNoAndUserNo", map);
	}
	public void modify(BoardVo boardvo) {
		sqlSession.update("board.modify", boardvo);
		
	}

	public void replyupdate(BoardVo boardvo) {
		sqlSession.update("board.replyupdate", boardvo);
	}

	public void replyinsert(BoardVo boardvo) {
		sqlSession.insert("board.replyinsert", boardvo);
	}

	public void hit(Long no) {
		sqlSession.update("board.hit", no);
	}
}
