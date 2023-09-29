package com.ssafy.attraction.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		String path = "";
		if (action.equals("list")) {
			path = list(request, response);
			forward(request, response, path);
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
	
	private String list(HttpServletRequest request, HttpServletResponse response) {
		String areaCode = request.getParameter("areaCode");
		String contentTypeId = request.getParameter("contentTypeId");
		String keyword = request.getParameter("keyword");
		try {
			List<AttractionDto> list = attractionService.searchAttraction(areaCode, contentTypeId, keyword);
			request.setAttribute("attractions", list);
			return "/map.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "관광지 정보 검색 중 문제 발생!!!");
			return "/error/error.jsp";
		}
	}

}
