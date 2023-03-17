package com.KoreaIT.example.JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.KoreaIT.example.JAM.dto.Articlecontroller;
import com.KoreaIT.example.JAM.dto.Membercontroller;

public class App {
	public void run() {

		Scanner sc = new Scanner(System.in);

		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/jdbc_article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

			conn = DriverManager.getConnection(url, "root", "");

			Articlecontroller articlecontroller = new Articlecontroller(conn, sc);
			Membercontroller membercontroller = new Membercontroller(conn, sc);

			while (true) {
				System.out.printf("명령어) ");
				String cmd = sc.nextLine().trim();

				if (cmd.equals("article write")) {
					articlecontroller.doWrite();
				} else if (cmd.startsWith("article list")) {
					articlecontroller.showList(cmd);
				} else if (cmd.startsWith("article modify ")) {
					articlecontroller.doModify(cmd);
				} else if (cmd.startsWith("article delete ")) {
					articlecontroller.doDelete(cmd);
				} else if (cmd.startsWith("article detail ")) {
					articlecontroller.showDetail(cmd);
				} else if (cmd.equals("member join")) {
					membercontroller.doJoin();
				} else if (cmd.equals("member login")) {
					membercontroller.doLogin();
				} else if (cmd.equals("member logout")) {
					membercontroller.doLogout();
				} else if (cmd.equals("member profile")) {
					membercontroller.showProfile();
				} 	else if (cmd.equals("exit")) {
					System.out.println("==프로그램 종료==");
					break;
				} else {
					System.out.println("잘못된 명령어입니다.");
					continue;
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);

		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		sc.close();
	}
}
