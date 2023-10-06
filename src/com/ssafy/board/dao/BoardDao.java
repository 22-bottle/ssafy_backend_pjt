package com.ssafy.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.board.dto.BoardDto;

public interface BoardDao {	
	void writeArticle(BoardDto boardDto) throws SQLException;
	List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;
}
