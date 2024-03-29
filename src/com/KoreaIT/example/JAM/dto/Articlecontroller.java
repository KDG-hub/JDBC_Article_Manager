package com.KoreaIT.example.JAM.dto;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.KoreaIT.example.Dto.Article;
import com.KoreaIT.example.JAM.Service.ArticleService;
import com.KoreaIT.example.JAM.util.Util;
import com.KoreaIT.example.session.session;

public class Articlecontroller {

	private Scanner sc;
	private ArticleService articleService;

	public Articlecontroller(Connection conn, Scanner sc) {
		articleService = new ArticleService(conn);
		this.sc = sc;
	}

	public void doWrite() {
		if (session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		System.out.println("== 게시물 작성 ==");
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		int id = articleService.write(title, body, session.loginedMember.id);

		System.out.printf("%d번 글이 생성되었습니다\n", id);
	};

	public void showList(String cmd) {
		System.out.println("== 게시물 목록 ==");

		List<Article> articles = new ArrayList<>();
		
		String keyword = cmd.substring("article list".length()).trim();

		if (keyword.length() > 0) {
		System.out.println("검색어 : "+ keyword);
		}

		List<Map<String, Object>> articleListMap = articleService.getArticles(keyword);

		for (Map<String, Object> articleMap : articleListMap) {
			articles.add(new Article(articleMap));
		}

		if (articles.size() == 0) {
			System.out.println("게시물이 없습니다");
			return;
		}

		System.out.println("번호	|	제목					|	작성자	|	조회수");

		for (Article article : articles) {
			System.out.printf("%d	|%s			|	%s	|	%d\n", article.id, article.title, article.name,article.views);
		}

	};
	
	public void showDetail(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);
		
		articleService.viewCount(id);

		Article article = articleService.getArticle(id);

		if (article == null) {
			System.out.printf("%d번글은 존재하지 않습니다.\n", id);
			return;
		}
	
		System.out.printf("게시글 번호 : %d\n", article.id);
		System.out.printf("작성날짜 : %s\n", Util.datetimeFormat(article.regDate));
		System.out.printf("수정날짜 : %s\n", Util.datetimeFormat(article.updateDate));
		System.out.printf("작성자 : %s\n", article.name);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
		System.out.printf("조회수 : %s\n", article.views);
	};

	public void doModify(String cmd) {
		if (session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		int id = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticle(id);

		if (article == null) {
			System.out.println("%d번글은 존재하지 않습니다.");
			return;
		}

		if (session.loginedMember.id != article.memberId) {
			System.out.printf("권한이 없습니다.\n");
			return;
		}

		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		articleService.update(title, body, id);

		System.out.printf("%d번 글이 수정되었습니다\n", id);
	};

	public void doDelete(String cmd) {
		if (session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		int id = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticle(id);

		if (article == null) {
			System.out.println("%d번글은 존재하지 않습니다.");
			return;
		}

		if (session.loginedMember.id != article.memberId) {
			System.out.println("권한이 없습니다.");
			return;
		}

		articleService.delete(id);

		System.out.printf("%d번 글이 삭제되었습니다\n", id);
	};
}
