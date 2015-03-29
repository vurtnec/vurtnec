package com.vurtnec.component.crawler;




import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.vurtnec.component.dbconn.DBConnection;
import com.vurtnec.component.logger.LoggerNames;
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
//			SqlSession sqlSession = getDbConnection().getSessionFactory().openSession();
//			try {
//				SyndFeed feed = CrawlerUtil.getRssSource(url);
//				ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
//				for (SyndEntry empty : feed.getEntries()) {
//					
//					String articleUrl = empty.getLink();
//					
//					if(CrawlerUtil.checkImported(articleUrl, articleMapper)) {
//						continue;
//					}
//					
//					Document articleDoc = CrawlerUtil.getHtml(articleUrl);
//					
//					Article article = CrawlerUtil.pupolateArticle(articleDoc, articleUrl);
//					
//					articleMapper.insertArticle(article);
//				}
//				sqlSession.commit();
//			}finally {
//				sqlSession.close();
//			}
		}
	}
	
	
	public DBConnection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
}
