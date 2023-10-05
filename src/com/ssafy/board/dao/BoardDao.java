package com.ssafy.board.dao;

import java.util.List;

import com.ssafy.board.dto.BoardDto;

public interface BoardDao {	
	int writeArticle(BoardDto boardDto);
	List<BoardDto> listArticle();
}
