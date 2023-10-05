package com.ssafy.attraction.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.attraction.dto.AttractionDto;
import com.ssafy.attraction.model.service.AttractionService;
import com.ssafy.attraction.model.service.AttractionServiceImpli;


@WebServlet("/attraction")
public class AttractionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AttractionService attractionService;

	public void init() {
		attractionService = AttractionServiceImpli.getAttractionService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		response.setCharacterEncoding("utf-8");

		String path = "";
		if (action.equals("map")) {
			path = "/attraction/map.jsp";
			redirect(request, response, path);
		} else if (action.equals("list")) {
			String areaCode = request.getParameter("areaCode");
			String contentTypeId = request.getParameter("contentTypeId");
			String keyword = request.getParameter("keyword");
			try {
				List<AttractionDto> list = attractionService.searchAttraction(areaCode, contentTypeId, keyword);
				ObjectMapper mapper = new ObjectMapper();
				Map<String, AttractionDto> resultMap = new HashMap<String, AttractionDto>();
				for (AttractionDto a : list) {
					resultMap.put(a.getContent_id(), a);
				}
				mapper.writeValue(response.getWriter(), resultMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

}
