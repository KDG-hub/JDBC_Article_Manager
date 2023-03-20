package com.KoreaIT.example.JAM.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;

public class ArticleDao {
	private Connection conn;

	public ArticleDao(Connection conn) {
		this.conn = conn;
	}

	public int write(String title, String body, int memberId) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append(", memberId = ?", memberId);

		return DBUtil.insert(conn, sql);
	}

	public List<Map<String, Object>> getArticles(String keyword) {
		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("INNER JOIN `member` AS m");
		sql.append("ON article.memberId = m.id");
		if (keyword.length() > 0) {
			sql.append("WHERE title like CONCAT('%',?,'%')", keyword);
		}
		sql.append("ORDER BY article.id DESC");
		return DBUtil.selectRows(conn, sql);
	}

	public int articleCount(int id) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(*)");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);
		return DBUtil.selectRowIntValue(conn, sql);
	}

	public void update(String title, String body, int id) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append("WHERE id = ?", id);

		DBUtil.update(conn, sql);
	}

	public void delete(int id) {
		SecSql sql = new SecSql();

		sql.append("DELETE");
		sql.append("FROM article");
		sql.append("WHERE id = ?", id);

		DBUtil.delete(conn, sql);

	}

	public Map<String, Object> getArticle(int id) {
		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("INNER JOIN `member` AS m");
		sql.append("ON article.memberId = m.id");
		sql.append("WHERE article.id = ?", id);

		return DBUtil.selectRow(conn, sql);
	}

	public void viewCount(int id) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET views = views +1");
		sql.append("WHERE id = ?", id);

		DBUtil.update(conn, sql);
	}

}
