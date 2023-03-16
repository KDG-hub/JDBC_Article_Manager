package com.KoreaIT.example.JAM.dto;

import java.sql.Connection;
import java.util.Scanner;

import com.KoreaIT.example.Dto.Member;
import com.KoreaIT.example.JAM.Service.MemberService;
import com.KoreaIT.example.JAM.util.Util;
import com.KoreaIT.example.session.session;

public class Membercontroller {
	private Scanner sc;
	MemberService memberService;

	public Membercontroller(Connection conn, Scanner sc) {
		this.memberService = new MemberService(conn);
		this.sc = sc;
	}

	public void doJoin() {
		if(session.isLogined()) {
			System.out.println("로그아웃 후 이용해주세요.");
			return;
		}
		
		System.out.println("== 회원가입 ==");

		String loginId = null;
		String loginPw = null;
		String loginPwChk = null;
		String name = null;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}

			boolean idDupChk = memberService.idDupChk(loginId);

			if (idDupChk) {
				System.out.println("중복된 아이디입니다. 다시 입력해주세요.");
				continue;
			}
			break;
		}
		while (true) {
			System.out.printf("패스워드 : ");
			loginPw = sc.nextLine().trim();
			if (loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해주세요.");
				continue;
			}

			boolean pwChk = true;
			while (true) {
				System.out.printf("패스워드 확인 : ");
				loginPwChk = sc.nextLine().trim();
				if (loginPw.length() == 0) {
					System.out.println("비밀번호확인을 입력해주세요.");
					continue;
				}
				if (loginPw.equals(loginPwChk) == false) {
					System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
					pwChk = false;
				}
				break;
			}
			if (pwChk) {
				break;
			}
		}
		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();
			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}
			break;
		}
		memberService.insert(loginId, loginPw, name);

		System.out.printf("%s회원님 가입을 환영합니다\n", name);

	}

	public void doLogin() {
		if(session.isLogined()) {
			System.out.println("로그아웃 후 이용해주세요.");
			return;
		}
		
		String loginId = null;
		String loginPw = null;
		System.out.println("==로그인==");
		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}

			System.out.printf("패스워드 : ");
			loginPw = sc.nextLine().trim();
			if (loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해주세요.");
				continue;
			}

			Member member = memberService.getMember(loginId);
			
			if (member == null) {
				System.out.println("존재하지 않는 아이디입니다. 다시 입력해주세요.");
				continue;
			}
			if(member.loginPw.equals(loginPw) == false) {
				System.out.println("비밀번호가 틀렸습니다. 비밀번호를 확인해주세요.");
				continue;
			}
			
			session.login(member);
			
			System.out.println("로그인 되었습니다.");
			
			break;
		}
	}

	public void doLogout() {
		if(session.isLogined() == false) {
			System.out.println("로그아웃 상태입니다.");
			return;
		}
		session.logout();
		System.out.println("로그아웃되었습니다.");
		
	}

	public void showProfile() {
		if(session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		
		Member member = session.loginedMember;
		
		System.out.printf("로그인 아이디: %s\n",member.loginId);
		System.out.printf("가입 날짜: %s\n",Util.datetimeFormat(member.regDate));
		System.out.printf("회원 이름: %s\n",member.name);
	}

}
