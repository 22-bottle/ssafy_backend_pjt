package com.ssafy.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.board.dto.BoardDto;
import com.ssafy.util.DBUtil;

public class BoardDaoImpl implements BoardDao {

	private static BoardDao boardDao = new BoardDaoImpl();
	private DBUtil dbUtil;
		
	private BoardDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static BoardDao getBoardDao() {
		if(boardDao == null)
			boardDao = new BoardDaoImpl();
		return boardDao;
	}
	
	@Override
	public int writeArticle(BoardDto boardDto) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into board (user_id, subject, content) \n");
			sql.append("values (?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getUser_id());
			pstmt.setString(2, boardDto.getSubject());
			pstmt.setString(3, boardDto.getContent());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		return cnt;
	}

	@Override
	public List<BoardDto> listArticle() {
		List<BoardDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select article_no, user_id, subject, content, register_time \n");
			sql.append("from board \n");
			sql.append("order by article_no desc");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setArticle_no(rs.getInt("article_no"));
				boardDto.setUser_id(rs.getString("user_id"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setRegister_time(rs.getString("register_time"));
				
				list.add(boardDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
}
