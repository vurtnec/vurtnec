package com.vurtnec.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vurtnec.component.crawler.CrawlerConfiguration;
import com.vurtnec.component.crawler.CrawlerUtil;
import com.vurtnec.component.dbconn.DBConnection;
import com.vurtnec.component.logger.LoggerNames;
import com.vurtnec.component.logger.LoggerUtil;
import com.vurtnec.model.bean.Article;
import com.vurtnec.model.impl.ArticleMapper;

@Controller
public class CrawlerController {
	
	private final static Logger logger = LoggerFactory.getLogger(LoggerNames.CONTROLLER);

	@Resource(name = "dbConnection")
	private DBConnection dbConnection;
	
	@Resource(name = "crawlerConfiguration")
	private CrawlerConfiguration crawlerConfiguration;
	
	@RequestMapping(value = { "/synchronize" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView login(String url) {
		logger.info("synchronize controller");
		ModelAndView mv = new ModelAndView();
//		Document doc = CrawlerUtil.getHtml(getCrawlerConfiguration().getUrl());
		
//		Elements articleUrls = getArticles(doc);

		SqlSession sqlSession = getDbConnection().getSessionFactory().openSession();
		ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
		try {
//			for (Element articleUrl : articleUrls) {
//				Document articleDoc = getArticle(articleUrl);
//				
//				Article article = pupolateArticle(articleDoc);
//				LoggerUtil.getInstantce().debug(logger, article, true);
//				articleMapper.insertArticle(article);
//			}
			Document doc = CrawlerUtil.getHtml(url);
			
			if(doc == null) {
				mv.setViewName("redirect:/error");
			}
			Article article = pupolateArticle(doc);
			
			LoggerUtil.getInstantce().debug(logger, article, true);
			
			articleMapper.insertArticle(article);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
		mv.setViewName("redirect:/home");
		return mv;
	}
	
	private Article pupolateArticle(Document articleDoc) {
		
		String content = getContent(articleDoc);
		
		Article article = new Article();
		article.setArticleTitle(getValue("vurtnecTitle", articleDoc));
		article.setArticleSubTitle(getValue("vurtnecSubTitle", articleDoc));
		article.setArticleAuthor(getValue("vurtnecAuthor", articleDoc));
		article.setArticleContent(content);
		article.setArticleCreateTime(new Timestamp(new Date().getTime()));
		
		article.setArticleImage(getValue("vurtnecImage", articleDoc));
		article.setCategoryId(Integer.valueOf(getValue("vurtnecCategory", articleDoc)));
		
		article.setArticlePreview(CrawlerUtil.delHTMLTag(content));
		
		return article;
	}
	
	private String getValue(String key, Document document) {
		String value = getCrawlerConfiguration().getMap().get(key);
		Element element = document.getElementById(key);
		if(element != null) {
			value = element.html();
		}
		return value;
	}
	
//	private Document getArticle(Element articleUrl) {
//		Node node = articleUrl.childNodes().get(0);
//		String url = node.attr("href");
//		return CrawlerUtil.getHtml(url);
//	}
//	
//	private Elements getArticles(Document doc) {
//		return doc.getElementsByClass("readAll");
//	}
	
	private String getContent(Document doc) {
		Element e = doc.getElementsByClass("nbw-blog-start").first();
		return e.nextElementSibling().html();
	}
	
	public DBConnection getDbConnection() {
		return dbConnection;
	}
	
	public void setDbConnection(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public CrawlerConfiguration getCrawlerConfiguration() {
		return crawlerConfiguration;
	}

	public void setCrawlerConfiguration(CrawlerConfiguration crawlerConfiguration) {
		this.crawlerConfiguration = crawlerConfiguration;
	}
}