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
	
	public List<BoardVo> findAllByPageAndKeyWord(int page, String keyword, int size){
		Map<String, Object> map = new HashMap<>();
		map.put("startOffset", (page-1)*size);
		map.put("keyword", keyword);
		map.put("size", size);
		return sqlSession.selectList("board.findAllByPageAndKeyWord", map);
	}
	
	public void insert(BoardVo vo) {
		sqlSession.insert("board.insert", vo);
	}
	
	public BoardVo view(Long no) {
//		return null;
		return sqlSession.selectOne("board.view", no);
		
	}
	
	public void modify(BoardVo vo) {
		sqlSession.update("board.modify", vo);
	}
	
	public void delete(BoardVo vo) {
		sqlSession.delete("board.delete", vo);
	}
	
	public int getTotalCount(String keyword) {
		return sqlSession.selectOne("board.getTotalCount", keyword);
	}
	
	public BoardVo findByNo(Long no) {
//		return null;
		return sqlSession.selectOne("board.findByNo", no);
	}

	
	

	
	public void replyInsert(BoardVo vo) {
		sqlSession.update("board.replyInsert", vo);
	}
}

