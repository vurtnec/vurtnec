package com.vurtnec.component.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.rometools.rome.feed.synd.SyndContent;
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
	
	public Document getHtml(SyndContent content) {
		return Jsoup.parse(content.getValue());
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
				if(reader != null) {
					reader.close();
				}
			} catch (IOException e) {
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
	
	private String getContent(Document articleDoc) {
		Elements eles = articleDoc.getElementsByTag("img");
		for (Element element : eles) {
			element.attr("src", "http://121.42.222.193/parseImg?url=" + element.attr("src"));
		}
		return articleDoc.toString();
	}
	
	private String getValue(String key, Document document) {
		String value = getMap().get(key);
		Element element = document.getElementById(key);
		if(element != null) {
			if("vurtnecImage".equals(key)) {
				value = "http://121.42.222.193/parseImg?url=" + element.html();
			} else {
				value = element.html();
			}
		}
		return value;
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
	
	
	public static void main(String[] args) throws IOException {
//		String url = "http://vurtnec2015.blog.163.com/";
		
//		Document doc = getHtml(url);
		
//		String html = "<div id='aaa'><img src='https://36.media.tumblr.com/832bebe213f7c21caac89241f4248623/tumblr_inline_nmjqi1UCYY1rffopq_540.jpg' /></div>";
//		
//		Document doc = Jsoup.parse(html);
//		Elements eles = doc.getElementsByTag("img");
//		for (Element element : eles) {
//			element.attr("src", "http://xlucom.aliapp.com/?url=" + element.attr("src"));
//			System.out.println(element);
//		}
//		System.out.println("articles:" + doc.getElementsByTag("img"));
		URL url = new URL("http://img2.ph.126.net/oNqz9OXX02WceiqwIUL8gA==/6630169464699125432.png");
		URLConnection con = url.openConnection();
		con.setConnectTimeout(5 * 1000);
		InputStream is = con.getInputStream();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String str;
		while ((str = in.readLine()) != null) {
			System.out.println(str);
			buffer.append(str);
		}
		System.out.println(buffer);
	}
	
	
//	private static Elements getArticles(Document doc) {
//		return doc.getElementsByClass("readAll");
//	}
}
