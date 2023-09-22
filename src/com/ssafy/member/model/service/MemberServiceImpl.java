package com.ssafy.member.model.service;

import com.ssafy.member.dto.MemberDto;
import com.ssafy.member.model.dao.MemberDao;
import com.ssafy.member.model.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService {

	private static MemberService memberService = new MemberServiceImpl();
	private MemberDao memberDao;
	
	private MemberServiceImpl() {
		memberDao = MemberDaoImpl.getMemberDao();
	}
	
	public static MemberService getMemberService() {
		return memberService;
	}

	@Override
	public MemberDto loginMember(String userId, String userPw) throws Exception {
		return memberDao.loginMember(userId, userPw);
	}

	@Override
	public int joinMember(MemberDto memberDto) throws Exception {
		return memberDao.joinMember(memberDto);
	}

	@Override
	public int idCheck(String checkid) throws Exception {
		return memberDao.idCheck(checkid);
	}
	
}
