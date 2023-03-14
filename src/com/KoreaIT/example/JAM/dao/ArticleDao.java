package com.KoreaIT.example.JAM.dao;

import java.sql.Connection;

public class ArticleDao {
	Connection conn;

	public ArticleDao(Connection conn) {
		this.conn = conn;
	}

}
