package com.ssafy.member.model.service;

import com.ssafy.member.dto.MemberDto;

public interface MemberService {

	MemberDto loginMember(String userId, String userPw) throws Exception;
	int joinMember(MemberDto memberDto) throws Exception;
	int idCheck(String checkid) throws Exception;
	void modifyMember(MemberDto memberDto) throws Exception;
	void withdrawMember(MemberDto memberDto) throws Exception;

}
