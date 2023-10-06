package com.ssafy.member.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
		} else if (action.equals("findform")) {
			path = "member/findform.jsp";
			forward(request, response, path);
		} else if (action.equals("findpwd")) {
			path = findpwd(request, response);
			forward(request, response, path);
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
	
	private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
	
	private String login(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("id");
		String userPw = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(request.getParameter("pw").getBytes());
			userPw = bytesToHex(md.digest());
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
				return "/member/loginform.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 중 에러 발생!!!");
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
		String pwd = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(request.getParameter("userpwd").getBytes());
			pwd = bytesToHex(md.digest());
			memberDto.setUserPwd(pwd);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
				String userPw = null;
				try {
					MessageDigest md = MessageDigest.getInstance("SHA-256");
					md.update(request.getParameter("userpwd").getBytes());
					userPw = bytesToHex(md.digest());
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				memberDto.setUserPwd(userPw);
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
	
	private String findpwd(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("id");
		try {
			int cnt = memberService.findPwd(userId);
			if(cnt != 0) {
				request.setAttribute("msg", "비밀번호가 1234로 초기화 되었습니다.");
				return "/member/findform.jsp";
			} else {
				request.setAttribute("msg", "존재하지 않는 아이디입니다.");
				return "/member/findform.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "비밀번호 찾기 중 에러 발생!!!");
			return "/error/error.jsp";
		}
	}

}
