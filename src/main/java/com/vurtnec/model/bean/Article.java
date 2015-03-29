package com.vurtnec.model.bean;

import java.sql.Timestamp;
public class Article {

	private int articleId;
	
	private String articleTitle;
	
	private String articleSubTitle;
	
	private String articleImage;
	
	private String articleContent;
	
	private String articleAuthor;
	
	private Timestamp articleCreateTime;
	
	private int categoryId;
	
	private Timestamp articleUpdateTime;
	
	private String articlePreview;
	
	private String articleHashCode;

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleSubTitle() {
		return articleSubTitle;
	}

	public void setArticleSubTitle(String articleSubTitle) {
		this.articleSubTitle = articleSubTitle;
	}

	public String getArticleImage() {
		return articleImage;
	}

	public void setArticleImage(String articleImage) {
		this.articleImage = articleImage;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getArticleAuthor() {
		return articleAuthor;
	}

	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}

	public Timestamp getArticleCreateTime() {
		return articleCreateTime;
	}

	public void setArticleCreateTime(Timestamp articleCreateTime) {
		this.articleCreateTime = articleCreateTime;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Timestamp getArticleUpdateTime() {
		return articleUpdateTime;
	}

	public void setArticleUpdateTime(Timestamp articleUpdateTime) {
		this.articleUpdateTime = articleUpdateTime;
	}

	public String getArticlePreview() {
		return articlePreview;
	}

	public void setArticlePreview(String articlePreview) {
		this.articlePreview = articlePreview;
	}

	public String getArticleHashCode() {
		return articleHashCode;
	}

	public void setArticleHashCode(String articleHashCode) {
		this.articleHashCode = articleHashCode;
	}
}
