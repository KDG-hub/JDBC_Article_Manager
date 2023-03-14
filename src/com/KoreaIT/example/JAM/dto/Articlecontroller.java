package com.KoreaIT.example.JAM.dto;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.KoreaIT.example.JAM.Article;
import com.KoreaIT.example.JAM.Service.ArticleService;

public class Articlecontroller {
	
	private Scanner sc;
	private ArticleService articleService;

	
	public Articlecontroller(Connection conn, Scanner sc) {
		articleService = new ArticleService(conn);
		this.sc = sc;
	}


	public void write() {
		System.out.println("== 게시물 작성 ==");

		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		int id = articleService.write(title, body);
				
		System.out.printf("%d번 글이 생성되었습니다\n", id);
	};

	public void list() {
		System.out.println("== 게시물 목록 ==");

		List<Article> articles = new ArrayList<>();

		List<Map<String, Object>> articleListMap = articleService.list();
				

		for (Map<String, Object> articleMap : articleListMap) {
			articles.add(new Article(articleMap));
		}

		if (articles.size() == 0) {
			System.out.println("게시물이 없습니다");
			return;
		}

		System.out.println("번호	|	제목");

		for (Article article : articles) {
			System.out.printf("%d	|	%s\n", article.id, article.title);
		}
		
	};

	public void modify(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);
		
		int articleCount = articleService.articleCount(id);

		if (articleCount == 0) {
			System.out.println("%d번글은 존재하지 않습니다.");
			return;
		}

		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		articleService.update(title, body, id);

		System.out.printf("%d번 글이 수정되었습니다\n", id);
	};

	public void delete(String cmd) {

		int id = Integer.parseInt(cmd.split(" ")[2]);

		int articleCount = articleService.articleCount(id);
		
		if (articleCount == 0) {
			System.out.println("%d번글은 존재하지 않습니다.");
			return;
		}

		articleService.delete(id);
		
		System.out.printf("%d번 글이 삭제되었습니다\n", id);

	};

	public void detail(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);
		
		Map<String, Object> articleMap = articleService.detail(id);

		if (articleMap.isEmpty()) {
			System.out.println("%d번글은 존재하지 않습니다.");
			return;
		}

		Article article = new Article(articleMap);
		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성날짜 : %s\n", article.regDate);
		System.out.printf("수정날짜 : %s\n", article.updateDate);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
	};
}
