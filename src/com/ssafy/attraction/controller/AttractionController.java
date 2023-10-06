package com.ssafy.attraction.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.google.gson.Gson;
import com.ssafy.attraction.dto.AttractionDto;
import com.ssafy.attraction.model.service.AttractionService;
import com.ssafy.attraction.model.service.AttractionServiceImpli;
import com.ssafy.attraction.dto.GugunDto;
import com.ssafy.attraction.dto.SidoDto;


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
			getSido(request, response);
			forward(request, response, path);
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
		} else if (action.equals("getGugun")) {
			String sido_code = request.getParameter("code");
			try {
				List<GugunDto> list = attractionService.getGugun(sido_code);
				Gson gson = new Gson();
				String jsonList = gson.toJson(list);
				response.setContentType("application/json; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print(jsonList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
	
	private void getSido(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			List<SidoDto> list = attractionService.getSido();
			request.setAttribute("sidoList", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
