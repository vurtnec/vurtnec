package com.vurtnec.component.crawler;



import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vurtnec.component.dbconn.DBConnection;
import com.vurtnec.component.logger.LoggerNames;
import com.vurtnec.component.logger.LoggerUtil;
import com.vurtnec.model.bean.Article;
import com.vurtnec.model.impl.ArticleMapper;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;

public class BlogCrawler extends WebCrawler {

	private final static Logger logger = LoggerFactory.getLogger(LoggerNames.CRAWLER);
	
	@Resource(name = "dbConnection")
	private DBConnection dbConnection;
	
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		
		if (page.getParseData() instanceof HtmlParseData) {
			Document doc = CrawlerUtil.getHtml(url);
			
			String content = getContent(doc);
			
			LoggerUtil.getInstantce().debug(logger, content);
			SqlSession sqlSession = getDbConnection().getSessionFactory().openSession();
			ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
			
			Article article = new Article();
			article.setArticleTitle(getTitle(doc));
			article.setArticleSubTitle("Problems look mighty small from 150 miles up");
			article.setArticleAuthor("vurtnec");
			article.setArticleContent("Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest ");
			article.setArticleCreateTime(new Timestamp(new Date().getTime()));
			article.setArticleImage("img/post-bg.jpg");
			article.setCategoryId(1);
			articleMapper.insertArticle(article);
			sqlSession.commit();
			
		}
	}
	
	private String getContent(Document doc) {
		Element e = doc.getElementsByClass("nbw-blog-start").first();
		return e.nextElementSibling().html();
	}
	
	private String getTitle(Document doc) {
		return null;
	}
	
	public DBConnection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
}
