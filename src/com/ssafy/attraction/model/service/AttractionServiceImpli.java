package com.ssafy.attraction.model.service;

import java.util.List;

import com.ssafy.attraction.dto.AttractionDto;
import com.ssafy.attraction.dto.GugunDto;
import com.ssafy.attraction.dto.SidoDto;
import com.ssafy.attraction.model.dao.AttractionDao;
import com.ssafy.attraction.model.dao.AttractionDaoImpl;

public class AttractionServiceImpli implements AttractionService {
	private static AttractionService attractionService = new AttractionServiceImpli();
	private AttractionDao attractionDao;
	
	private AttractionServiceImpli() {
		attractionDao = AttractionDaoImpl.getAttractionDao();
	}
	
	public static AttractionService getAttractionService() {
		return attractionService;
	}

	@Override
	public List<AttractionDto> searchAttraction(String sido, String content_id, String title) throws Exception {
		return attractionDao.searchAttraction(sido, content_id, title);
	}

	@Override
	public List<SidoDto> getSido() throws Exception {
		return attractionDao.getSido();
	}

	@Override
	public List<GugunDto> getGugun(String sido_code) throws Exception {
		return attractionDao.getGugun(sido_code);
	}
		
}
