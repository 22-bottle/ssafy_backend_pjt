package com.ssafy.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.board.dao.BoardDaoImpl;
import com.ssafy.board.dto.BoardDto;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String path = "";
		if (action.equals("writeform")) {
			path = "board/writeform.jsp";
			forward(request,response,path);
		}
		
		List<BoardDto> list = BoardDaoImpl.getBoardDao().listArticle();
//		System.out.println(list.size());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BoardDto boardDto = new BoardDto();
		boardDto.setUser_id(request.getParameter("userid"));
		boardDto.setSubject(request.getParameter("subject"));
		boardDto.setContent(request.getParameter("content"));
		System.out.println(boardDto);
		
		int cnt = BoardDaoImpl.getBoardDao().writeArticle(boardDto);
	}
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

}
