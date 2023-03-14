package com.KoreaIT.example.JAM.Service;

import java.sql.Connection;

import com.KoreaIT.example.JAM.dao.ArticleDao;

public class ArticleService {
	Connection conn;
	ArticleDao articleDao;
	public ArticleService(Connection conn) {
		articleDao = new ArticleDao(conn);
	}

}
