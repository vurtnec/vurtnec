package com.vurtnec.component.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.vurtnec.model.bean.Article;
import com.vurtnec.model.impl.ArticleMapper;

public class CrawlerUtil {
	
	public static Map<String, String> getMap() {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("vurtnecTitle", "Essay");
		map.put("vurtnecSubTitle", "for my own");
		map.put("vurtnecAuthor", "Vurtnec");
		map.put("vurtnecCategory", "1");
		map.put("vurtnecImage", "");
		
		return map;
	}
	
//	private final static Logger logger = LoggerFactory.getLogger(CrawlerUtil.class);
	
	public Document getHtml(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
//			LoggerUtil.getInstantce().debug(logger, doc);
//			System.out.println(doc);
			
		} catch (IOException e) {
//			logger.error("error occues wihle connect url:" + url, e);
			e.printStackTrace();
		}
		return doc;
	}
	
	
	public SyndFeed getRssSource(String url) {
		
		SyndFeed feed = null;
		XmlReader reader = null;
		try {
			reader = new XmlReader(new URL(url));
			SyndFeedInput input = new SyndFeedInput();
			feed = input.build(reader);
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (FeedException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return feed;
	}
	
	public Article pupolateArticle(Document articleDoc, String url) {
		
		String content = getContent(articleDoc);
		
		Article article = new Article();
		
		article.setArticleHashCode(String.valueOf(url.hashCode()));
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
		String value = getMap().get(key);
		Element element = document.getElementById(key);
		if(element != null) {
			value = element.html();
		}
		return value;
	}
	
	private String getContent(Document doc) {
		Element e = doc.getElementsByClass("nbw-blog-start").first();
		return e.nextElementSibling().html();
	}
	
	public static String delHTMLTag(String htmlStr){ 
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
         
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //过滤script标签 
         
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 
        
        
        return htmlStr.length() >= 200 ? htmlStr.substring(0,200) : htmlStr; //返回文本字符串 
    } 
	
	/**
	 * 
	 * @param url
	 * @param articleMapper
	 * @return true is imported, false is not.
	 */
	public boolean checkImported(String url, ArticleMapper articleMapper) {
		return articleMapper.findArticleByHash(String.valueOf(url.hashCode())) != 0;
	}
	
	
//	public static void main(String[] args) {
//		String url = "http://vurtnec2015.blog.163.com/";
//		
//		Document doc = getHtml(url);
//		
////		String html = " <div class=\"nbw-blog-start\">11</div> <div class=\"bct fc05 fc11 nbw-blog ztag\">22222</div>";
////		
////		Document doc = Jsoup.parse(html);
//		System.out.println(doc);
//		System.out.println("articles:" + getArticles(doc));
//	}
	
	
//	private static Elements getArticles(Document doc) {
//		return doc.getElementsByClass("readAll");
//	}
}
