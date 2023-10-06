package com.ssafy.reply.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ssafy.reply.dto.ReplyDto;
import com.ssafy.util.DBUtil;

public class ReplyDaoImpl implements ReplyDao {
	
	private static ReplyDao replyDao = new ReplyDaoImpl();
	private DBUtil dbUtil;
		
	private ReplyDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static ReplyDao getReplyDao() {
		if(replyDao == null)
			replyDao = new ReplyDaoImpl();
		return replyDao;
	}

	@Override
	public void write(ReplyDto replyDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into reply (article_no, user_id, content) \n");
			sql.append("values (?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, replyDto.getArticle_no());
			pstmt.setString(2, replyDto.getUser_id());
			pstmt.setString(3, replyDto.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

}
