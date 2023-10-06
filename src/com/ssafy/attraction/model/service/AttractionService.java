package com.ssafy.attraction.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.attraction.dto.AttractionDto;
import com.ssafy.attraction.dto.GugunDto;
import com.ssafy.attraction.dto.SidoDto;

public interface AttractionService {
	List<AttractionDto> searchAttraction(String sido, String content_id, String title) throws Exception;

	List<SidoDto> getSido() throws Exception;

	List<GugunDto> getGugun(String sido_code) throws Exception;
}
