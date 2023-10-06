package com.ssafy.attraction.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.attraction.dto.AttractionDto;
import com.ssafy.attraction.dto.GugunDto;
import com.ssafy.attraction.dto.SidoDto;

public interface AttractionDao {
	List<AttractionDto> searchAttraction(String sido, String content_id, String title) throws SQLException;

	List<SidoDto> getSido() throws SQLException ;

	List<GugunDto> getGugun(String sido_code) throws SQLException;
}
