package com.vurtnec.model.impl;

import java.util.List;
import java.util.Map;

import com.vurtnec.model.bean.Article;

 
public interface ArticleMapper {
	
	public List<Article> findAllArticleOrderByTime();
	
	public List<Article> findArticlePageOrderByTime(int pageNow);
    
    public List<Article> findArticleByCategoryIdOrderByTime(int categoryId);
    
    public Article findArticleById(int articleId);
    
    public int findArticleCount();
    
    public void insertArticle(Article article);
    
    public int findArticleByHash(String articleHashCode);
    
    public int findCategoryArticleCount(int categoryId);
    
    public List<Article> findCategoryArticlePageOrderByTime(Map<String,Integer> map);
} 