package com.ssafy.member.model.dao;

import java.sql.SQLException;

import com.ssafy.member.dto.MemberDto;

public interface MemberDao {

	MemberDto loginMember(String userId, String userPw) throws SQLException;
	int joinMember(MemberDto memberDto) throws SQLException;
	int idCheck(String checkid) throws SQLException;
	void modifyMember(MemberDto memberDto) throws SQLException;
	void withdrawMember(MemberDto memberDto) throws SQLException;
	int findPwd(String userId) throws SQLException;

}