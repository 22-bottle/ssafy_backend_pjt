package com.ssafy.attraction.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.attraction.dto.AttractionDto;

public interface AttractionDao {
	List<AttractionDto> searchAttraction(String sido, String content_id, String title) throws SQLException;
}
