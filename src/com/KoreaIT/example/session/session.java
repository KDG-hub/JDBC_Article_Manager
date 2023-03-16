package com.KoreaIT.example.session;

import com.KoreaIT.example.Dto.Member;

public class session {
	public static int loginedMemberId;
	public static Member loginedMember;
	
	static {
		loginedMemberId = -1;
	}
	
	public static void login(Member member){
		loginedMemberId = member.id;
		loginedMember = member;
	}
	
	public static void logout() {
		loginedMemberId = -1;
		loginedMember = null;
	}
	
	public static boolean isLogined() {
		return loginedMemberId != -1;
	}
}
