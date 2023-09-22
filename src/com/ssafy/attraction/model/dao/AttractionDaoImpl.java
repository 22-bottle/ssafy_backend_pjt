package com.ssafy.attraction.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.attraction.dto.AttractionDto;
import com.ssafy.util.DBUtil;


public class AttractionDaoImpl implements AttractionDao{

	private static AttractionDao attractionDao;
	private DBUtil dbUtil;
	
	private AttractionDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static AttractionDao getAttractionDao() {
		if (attractionDao == null)
			attractionDao = new AttractionDaoImpl();
		return attractionDao;
	}
	
	@Override
	public List<AttractionDto> searchAttraction(String sido, String content_id, String title) throws SQLException {
		List<AttractionDto> list = new ArrayList<AttractionDto>();
		AttractionDto attractionDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * ");
			sql.append("from attraction_info where 1 \n");
			if (sido != null) sql.append("and sido_code = " + sido + " ");
			sql.append("where sido_code = ? and content_type_id = ? and keyword like concat('%', ?, '%') \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, sido);
			pstmt.setString(2, content_id);
			pstmt.setString(3, title);
			while (rs.next()) {
				attractionDto = new AttractionDto();
				attractionDto.setSido_code(rs.getString("sido_code"));
				attractionDto.setContent_type_id(rs.getString("content_type_id"));
				attractionDto.setTitle(rs.getString("title"));
				list.add(attractionDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	
}
