package com.vurtnec.model.impl;

import java.util.List;

import com.vurtnec.model.bean.Article;

 
public interface ArticleMapper {
	
	public List<Article> findAllArticleOrderByTime();
    
    public List<Article> findArticleByCategoryIdOrderByTime(int categoryId);
    
    public Article findArticleById(int articleId);
    
    public void insertArticle(Article article);
} 