package com.vurtnec.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.vurtnec.component.dbconn.DBConnection;
import com.vurtnec.component.logger.LoggerNames;
import com.vurtnec.component.logger.LoggerUtil;
import com.vurtnec.model.bean.Article;
import com.vurtnec.model.bean.Category;
import com.vurtnec.model.impl.ArticleMapper;
import com.vurtnec.model.impl.CategoryMapper;

@Controller
public class VurtnecController {
	
	private final static Logger logger = LoggerFactory.getLogger(LoggerNames.CONTROLLER);
	
	@Resource(name = "dbConnection")
	private DBConnection dbConnection;

	
	@RequestMapping(value = { "/home" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView home(String page) {
		if(Strings.isNullOrEmpty(page)) {
			page = "1";
		}
		LoggerUtil.getInstantce().debug(logger, "home page enter. and page is " + page);
		List<Category> categorys = null;
		List<Article> articles = null;
		int totalNum = 0;
		
		int	pageStr = Integer.valueOf(page);
		
		int pageNow = (pageStr -1) * 5;
		
		SqlSession sqlSession = getDbConnection().getSessionFactory().openSession();
		try {
			CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
			categorys = categoryMapper.findAllCategory();
			
			ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
			articles = articleMapper.findArticlePageOrderByTime(pageNow);
			
			totalNum = articleMapper.findArticleCount();
			
		} finally {
			sqlSession.close();
		}
		LoggerUtil.getInstantce().debug(logger, "categorys:" + categorys + ", article:" + articles);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("categorys", categorys);
		mv.addObject("articles", articles);
		mv.addObject("currentPage", pageStr);
		mv.addObject("totalPageNum", totalPageNum(totalNum));
		mv.setViewName("/front/home");
		
		return mv;
	}
	
	

	@RequestMapping(value = { "/category" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView category(String page, String categoryId) {
		if(Strings.isNullOrEmpty(page) || Strings.isNullOrEmpty(categoryId)) {
			page = "1";
		}
		LoggerUtil.getInstantce().debug(logger, "home page enter. and page is " + page);
		List<Category> categorys = null;
		List<Article> articles = null;
		int totalNum = 0;
		
		int	pageStr = Integer.valueOf(page);
		
		int pageNow = (pageStr -1) * 5;
		
		SqlSession sqlSession = getDbConnection().getSessionFactory().openSession();
		try {
			CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
			categorys = categoryMapper.findAllCategory();
			
			ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			
			map.put("categoryId", Integer.valueOf(categoryId));
			map.put("pageNow", pageNow);
			
			articles = articleMapper.findCategoryArticlePageOrderByTime(map);
			
			totalNum = articleMapper.findCategoryArticleCount(Integer.valueOf(categoryId));
			
		} finally {
			sqlSession.close();
		}
		LoggerUtil.getInstantce().debug(logger, "categorys:" + categorys + ", article:" + articles);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("categorys", categorys);
		mv.addObject("articles", articles);
		mv.addObject("currentPage", pageStr);
		mv.addObject("totalPageNum", totalPageNum(totalNum));
		mv.setViewName("/front/home");
		
		return mv;
	}
	
	
	@RequestMapping(value = { "/post" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView post(String articleId) {
		ModelAndView mv = new ModelAndView();
		if(Strings.isNullOrEmpty(articleId)) {
			LoggerUtil.getInstantce().debug(logger, "post page enter and article id is empty.");
			mv.setViewName("/error");
			return mv;
		}
		LoggerUtil.getInstantce().debug(logger, "post page enter and article id is" + articleId);
		List<Category> categorys = null;
		Article article = null;
		
		SqlSession sqlSession = getDbConnection().getSessionFactory().openSession();
		try {
			CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
			categorys = categoryMapper.findAllCategory();
			
			ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
			article = articleMapper.findArticleById(Integer.valueOf(articleId));
		} finally {
			sqlSession.close();
		}
		if(article == null) {
			mv.setViewName("redirect:/home");
			return mv;
		}
		LoggerUtil.getInstantce().debug(logger, article.getArticleContent());
		mv.addObject("article", article);
		mv.addObject("categorys", categorys);
		mv.setViewName("/front/post");
		return mv;
	}
	
	@RequestMapping(value = { "/about" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView about() {
		LoggerUtil.getInstantce().debug(logger, "about page enter.");
		ModelAndView mv = new ModelAndView();
		List<Category> categorys = null;
		SqlSession sqlSession = getDbConnection().getSessionFactory().openSession();
		try {
			CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
			categorys = categoryMapper.findAllCategory();
		} finally {
			sqlSession.close();
		}
		mv.addObject("categorys", categorys);
		mv.setViewName("/front/about");
		return mv;
	}
	
	@RequestMapping(value = { "/contact" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView contact() {
		LoggerUtil.getInstantce().debug(logger, "contact page enter.");
		ModelAndView mv = new ModelAndView();
		List<Category> categorys = null;
		SqlSession sqlSession = getDbConnection().getSessionFactory().openSession();
		try {
			CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
			categorys = categoryMapper.findAllCategory();
		} finally {
			sqlSession.close();
		}
		mv.addObject("categorys", categorys);
		mv.setViewName("/front/contact");
		return mv;
	}
	
	private int totalPageNum(int totalNum) {
		return (totalNum + 5 - 1) / 5;
	}

	public DBConnection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public static void main(String[] args) {
		System.out.println((1 + 5 - 1) / 5);
	}
}
