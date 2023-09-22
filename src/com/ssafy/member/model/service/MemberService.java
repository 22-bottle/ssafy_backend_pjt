package com.ssafy.member.model.service;

import com.ssafy.member.dto.MemberDto;

public interface MemberService {

	MemberDto loginMember(String userId, String userPw) throws Exception;

}
