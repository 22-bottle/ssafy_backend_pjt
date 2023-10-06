package com.ssafy.reply.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.board.model.BoardDto;
import com.ssafy.member.dto.MemberDto;
import com.ssafy.reply.dto.ReplyDto;
import com.ssafy.reply.model.service.ReplyService;
import com.ssafy.reply.model.service.ReplyServiceImpl;

@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReplyService replyService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		replyService = ReplyServiceImpl.getReplyService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String path = "";
		if (action.equals("write")) {
			path = write(request, response);
			redirect(request, response, path);
		}
	}

	private String write(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto)session.getAttribute("userInfo");
		if (memberDto != null) {
			ReplyDto replyDto = new ReplyDto();
			replyDto.setUser_id(memberDto.getUserId());
			replyDto.setContent(request.getParameter("content"));
			replyDto.setArticle_no(request.getParameter("article_no"));
			System.out.println(replyDto.getContent());
			try {
				replyService.write(replyDto);
				return "/article?action=view&articleno=" + replyDto.getArticle_no();
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글작성 중 문제 발생!!!");
				return "/error/error.jsp";
			}
		} else
			return "/member/loginform.jsp";
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
