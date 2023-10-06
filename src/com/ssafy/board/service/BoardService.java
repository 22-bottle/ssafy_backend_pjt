package com.ssafy.board.service;

import java.util.List;
import java.util.Map;

import com.ssafy.board.dto.BoardDto;

public interface BoardService {
	void writeArticle(BoardDto boardDto) throws Exception;
	List<BoardDto> listArticle(Map<String, String> map) throws Exception;
}
