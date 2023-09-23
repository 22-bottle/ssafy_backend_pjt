package com.ssafy.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.member.dto.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.member.model.service.MemberServiceImpl;

@WebServlet("/member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService;
	
	public void init() {
		memberService = MemberServiceImpl.getMemberService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		String path = "";
		if (action.equals("loginform")) {
			path = "member/loginform.jsp";
			forward(request, response, path);
		} else if (action.equals("login")) {
			path = login(request, response);
			forward(request, response, path);
		} else if (action.equals("logout")) {
			path = logout(request, response);
			redirect(request, response, path);
		} else if (action.equals("joinform")) {
			path = "member/joinform.jsp";
			forward(request, response, path);
		} else if (action.equals("join")) {
			path = join(request, response);
			redirect(request, response, path);
		} else if (action.equals("idcheck")) {
			String checkid = request.getParameter("checkid");
			int cnt = 1;
			try {
				cnt = memberService.idCheck(checkid);
			} catch (Exception e) {
				e.printStackTrace();
				cnt = 1;
			}
			response.setContentType("text/plain;charset=utf-8");
			response.getWriter().print(checkid + "," + cnt);
		} else if (action.equals("mypage")) {
			path = "member/mypage.jsp";
			forward(request, response, path);
		} else if (action.equals("modify")) {
			path = modify(request, response);
			redirect(request, response, path);
		} else if (action.equals("withdraw")) {
			path = withdraw(request, response);
			redirect(request, response, path);
		} else {
			redirect(request, response, path);
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
	
	private String login(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("id");
		String userPw = request.getParameter("pw");
		try {
			MemberDto memberDto = memberService.loginMember(userId, userPw);
			if(memberDto != null) {
				HttpSession session = request.getSession();
				session.setAttribute("userInfo", memberDto);
				
				String idsave = request.getParameter("remember");
				if("on".equals(idsave)) {
					Cookie cookie = new Cookie("user_id", userId);
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(60 * 60 * 24);
					response.addCookie(cookie);
				} else {
					Cookie cookies[] = request.getCookies();
					if(cookies != null) {
						for(Cookie cookie : cookies) {
							if("user_id".equals(cookie.getName())) {
								cookie.setMaxAge(0);
								response.addCookie(cookie);
								break;
							}
						}
					}
				}
				return "/index.jsp";
			} else {
				request.setAttribute("msg", "�븘�씠�뵒 �삉�뒗 鍮꾨�踰덊샇 �솗�씤 �썑 �떎�떆 濡쒓렇�씤�븯�꽭�슂.");
				return "/member/loginform.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "濡쒓렇�씤 以� �뿉�윭 諛쒖깮!!!");
			return "/error/error.jsp";
		}
	}
	
	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "";
	}
	
	private String join(HttpServletRequest request, HttpServletResponse response) {
		MemberDto memberDto = new MemberDto();
		memberDto.setUserName(request.getParameter("username"));
		memberDto.setUserId(request.getParameter("userid"));
		memberDto.setUserPwd(request.getParameter("userpwd"));
		memberDto.setEmailId(request.getParameter("emailid"));
		memberDto.setEmailDomain(request.getParameter("emaildomain"));
		try {
			memberService.joinMember(memberDto);
			return "/index.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원가입 중 오류 발생!!!");
			return "/error/error.jsp";
		}
	}
	
	private String modify(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
			if(memberDto != null) {
				memberDto.setUserName(request.getParameter("username"));
				memberDto.setUserPwd(request.getParameter("userpwd"));
				memberService.modifyMember(memberDto);
				return "/index.jsp";
			} else 
				return "/member/login.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원정보 수정 중 오류 발생!!!");
			return "/error/error.jsp";
		}
	}
	
	private String withdraw(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
			if(memberDto != null) {
				System.out.println(memberDto.getUserId());
				memberService.withdrawMember(memberDto);
				session.invalidate();
				return "";
			} else 
				return "/member/login.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원 탈퇴 중 오류 발생!!!");
			return "/error/error.jsp";
		}
	}

}
