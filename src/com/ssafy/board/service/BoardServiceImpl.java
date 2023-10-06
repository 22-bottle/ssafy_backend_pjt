package com.ssafy.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.board.dao.BoardDao;
import com.ssafy.board.dao.BoardDaoImpl;
import com.ssafy.board.dto.BoardDto;

public class BoardServiceImpl implements BoardService{
	private static BoardService boardService = new BoardServiceImpl();
	private BoardDao boardDao;
	
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getBoardDao();
	}

	public static BoardService getBoardService() {
		return boardService;
	}
	
	@Override
	public void writeArticle(BoardDto boardDto) throws Exception {
		boardDao.writeArticle(boardDto);
	}
	
	@Override
	public List<BoardDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
//		if("userid".equals(key))
//			key = "user_id";
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
//		int pgno = Integer.parseInt(map.get("pgno"));
//		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
//		param.put("start", start);
//		param.put("listsize", SizeConstant.LIST_SIZE);
		return boardDao.listArticle(param);
	}
	
	
}
