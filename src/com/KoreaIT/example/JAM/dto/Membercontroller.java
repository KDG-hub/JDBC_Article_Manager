package com.KoreaIT.example.JAM.dto;

import java.sql.Connection;
import java.util.Scanner;

import com.KoreaIT.example.JAM.Service.MemberService;

public class Membercontroller {
	private Scanner sc;
	MemberService memberService;

	public Membercontroller(Connection conn, Scanner sc) {
		this.memberService = new MemberService(conn);
		this.sc = sc;
	}

	public void join() {
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
}
