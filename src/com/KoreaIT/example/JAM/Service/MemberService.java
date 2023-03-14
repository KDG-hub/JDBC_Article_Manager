package com.KoreaIT.example.JAM.Service;

import java.sql.Connection;

import com.KoreaIT.example.JAM.dao.MemberDao;

public class MemberService {
	MemberDao memberDao;
	
	public MemberService(Connection conn) {
		memberDao = new MemberDao(conn);
	}

	public boolean idDupChk(String loginId) {
		return MemberDao.idDupChk(loginId);
	}

	public void insert(String loginId, String loginPw, String name) {
		memberDao.insert( loginId,  loginPw,  name);
	}

}
