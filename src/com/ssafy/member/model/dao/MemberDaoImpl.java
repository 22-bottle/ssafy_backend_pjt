package com.ssafy.member.model.dao;

import com.ssafy.util.DBUtil;

public class MemberDaoImpl implements MemberDao {

	private static MemberDao memberDao = new MemberDaoImpl();
	private DBUtil dbUtil;
	
	private MemberDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static MemberDao getMemberDao() {
		return memberDao;
	}
	
}