package com.vurtnec.model.impl;

import java.util.List;

import com.vurtnec.model.bean.Article;

 
public interface ArticleMapper {
	
	public List<Article> findAllArticleOrderByTime();
	
	public List<Article> findArticlePageOrderByTime(int pageNow);
    
    public List<Article> findArticleByCategoryIdOrderByTime(int categoryId);
    
    public Article findArticleById(int articleId);
    
    public int findArticleCount();
    
    public void insertArticle(Article article);
} 