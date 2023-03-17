package com.KoreaIT.example.JAM.Service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.example.Dto.Article;
import com.KoreaIT.example.JAM.dao.ArticleDao;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService(Connection conn) {
		articleDao = new ArticleDao(conn);
	}

	public int write(String title, String body, int memberId) {
		return articleDao.write(title, body, memberId);
	}

	public List<Map<String, Object>> list() {
		return articleDao.list();
	}

	public int articleCount(int id) {
		return articleDao.articleCount(id);
	}

	public void update(String title, String body, int id) {
		articleDao.update(title, body, id);
	}

	public void delete(int id) {
		articleDao.delete(id);

	}

	public Article getArticle(int id) {
		
		Map<String, Object> articleMap = articleDao.getArticle(id);
		
		if (articleMap.isEmpty()) {
			return null;
		}
		
		return new Article(articleMap);
	}

	public List<Map<String, Object>> searchKeyword(String keyword) {
		return articleDao.searchKeyword(keyword);
	}

	public void upViews(int id) {
		articleDao.upViews(id);
	}

}
