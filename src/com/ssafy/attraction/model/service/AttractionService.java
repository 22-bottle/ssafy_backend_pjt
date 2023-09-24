package com.ssafy.attraction.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.attraction.dto.AttractionDto;

public interface AttractionService {
	List<AttractionDto> searchAttraction(String sido, String content_id, String title) throws Exception;
}
