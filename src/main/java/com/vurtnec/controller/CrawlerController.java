package com.vurtnec.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

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

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.vurtnec.component.crawler.BlogCrawler;
import com.vurtnec.component.crawler.CrawlerConfiguration;
import com.vurtnec.component.crawler.CrawlerUtil;
import com.vurtnec.component.dbconn.DBConnection;
import com.vurtnec.component.logger.LoggerNames;
import com.vurtnec.component.logger.LoggerUtil;
import com.vurtnec.model.bean.Article;
import com.vurtnec.model.impl.ArticleMapper;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

@Controller
public class CrawlerController {
	
	private final static Logger logger = LoggerFactory.getLogger(LoggerNames.CONTROLLER);

	@Resource(name = "dbConnection")
	private DBConnection dbConnection;
	
	@Resource(name = "crawlerConfiguration")
	private CrawlerConfiguration crawlerConfiguration;
	
	@Resource(name = "crawlerUtil")
	private CrawlerUtil crawlerUtil;
	
	@RequestMapping(value = { "/synchronize" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView login(String url) {
		logger.info("synchronize controller");
		ModelAndView mv = new ModelAndView();

		SqlSession sqlSession = getDbConnection().getSessionFactory().openSession();
		ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
		try {
			
			if(getCrawlerUtil().checkImported(url, articleMapper)) {
				mv.setViewName("redirect:/home");
				return mv;
			}
			
			Document doc = getCrawlerUtil().getHtml(url);
			
			if(doc == null) {
				mv.setViewName("redirect:/error");
			}
			Article article = getCrawlerUtil().pupolateArticle(doc, url);
			
			LoggerUtil.getInstantce().debug(logger, article, true);
			
			articleMapper.insertArticle(article);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
		mv.setViewName("redirect:/home");
		return mv;
	}
	
	@RequestMapping(value = { "/startCrawler" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView start() {
		logger.info("start controller");
		ModelAndView mv = new ModelAndView();
//		try {
//			CrawlConfig config = new CrawlConfig();
//			PageFetcher pageFetcher = new PageFetcher(config);
//			RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
//			RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
//			
//			CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
//			
//			controller.addSeed(getCrawlerConfiguration().getUrl());  
//			controller.start(BlogCrawler.class, 1); 
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		SqlSession sqlSession = getDbConnection().getSessionFactory().openSession();
		try {
			SyndFeed feed = getCrawlerUtil().getRssSource(getCrawlerConfiguration().getUrl() + "?param=" + Math.random()*100 + 1);
			
			if(feed == null) {
				mv.addObject("message", "import failed rss feed is empty.");
				mv.setViewName("redirect:/error");
				return mv;
			}
			
			ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
			for (SyndEntry empty : feed.getEntries()) {
				
				String articleUrl = empty.getLink();
				
				if(getCrawlerUtil().checkImported(articleUrl, articleMapper)) {
					continue;
				}
				SyndContent content = empty.getDescription();
				Document articleDoc = getCrawlerUtil().getHtml(content);
				
				Article article = getCrawlerUtil().pupolateArticle(articleDoc, articleUrl);
				
				articleMapper.insertArticle(article);
			}
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
		mv.addObject("message", "import successed");
		mv.setViewName("redirect:/admin/home");
		return mv;
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

	public CrawlerUtil getCrawlerUtil() {
		return crawlerUtil;
	}

	public void setCrawlerUtil(CrawlerUtil crawlerUtil) {
		this.crawlerUtil = crawlerUtil;
	}
}