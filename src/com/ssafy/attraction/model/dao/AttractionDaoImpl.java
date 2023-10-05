package com.ssafy.attraction.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.attraction.dto.AttractionDto;
import com.ssafy.util.DBUtil;

public class AttractionDaoImpl implements AttractionDao {
	
	private static AttractionDao attractionDao = new AttractionDaoImpl();
	private DBUtil dbUtil;
	
	private AttractionDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static AttractionDao getAttractionDao() {
		return attractionDao;
	}

	@Override
	public List<AttractionDto> searchAttraction(String sido, String content_id, String title) throws SQLException {
		List<AttractionDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * \n");
			sql.append("from attraction_info \n");
			sql.append("where sido_code = ? and content_id = ? and title = %?%");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, sido);
			pstmt.setString(2, content_id);
			pstmt.setString(3, title);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AttractionDto attractionDto = new AttractionDto();
				attractionDto.setContent_id(rs.getString("content_id"));
				attractionDto.setContent_type_id(rs.getString("content_type_id"));
				attractionDto.setTitle(rs.getString("title"));
				attractionDto.setAddr1(rs.getString("addr1"));
				attractionDto.setAddr2(rs.getString("addr2"));
				attractionDto.setZipcode(rs.getString("zipcode"));
				attractionDto.setTel(rs.getString("tel"));
				attractionDto.setFirst_image(rs.getString("first_image"));
				attractionDto.setFirst_image2(rs.getString("first_image2"));
				attractionDto.setReadcount(rs.getString("readcount"));
				attractionDto.setSido_code(rs.getString("sido_code"));
				attractionDto.setGugun_code(rs.getString("gugun_code"));
				attractionDto.setLatitude(rs.getString("latitude"));
				attractionDto.setLongtitude(rs.getString("longitude"));
				attractionDto.setMlevel(rs.getString("mlevel"));
				list.add(attractionDto);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

}
