package com.KoreaIT.example.Dto;

import java.time.LocalDateTime;
import java.util.Map;

public class Article {
	public int memberId;
	public int id;
	public LocalDateTime regDate;
	public LocalDateTime updateDate;
	public String title;
	public String body;
	public String name;
	public int views;
	
	public Article(Map<String, Object> articleMap) {
		this.id = (int) articleMap.get("id");
		this.regDate = (LocalDateTime) articleMap.get("regDate");
		this.updateDate = (LocalDateTime) articleMap.get("updateDate");
		this.title = (String) articleMap.get("title");
		this.body = (String) articleMap.get("body");
		this.name = (String) articleMap.get("name");
		this.memberId = (int) articleMap.get("memberId");
		this.views = (int) articleMap.get("views");
	}
}
