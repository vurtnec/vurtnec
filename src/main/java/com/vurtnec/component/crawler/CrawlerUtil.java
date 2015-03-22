package com.vurtnec.component.crawler;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class CrawlerUtil {
	
//	private final static Logger logger = LoggerFactory.getLogger(CrawlerUtil.class);
	
	public static Document getHtml(String url) {
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

        return htmlStr.substring(0,200); //返回文本字符串 
    } 
	
	
	public static void main(String[] args) {
		String url = "http://vurtnec2015.blog.163.com/";
		
		Document doc = CrawlerUtil.getHtml(url);
		
//		String html = " <div class=\"nbw-blog-start\">11</div> <div class=\"bct fc05 fc11 nbw-blog ztag\">22222</div>";
//		
//		Document doc = Jsoup.parse(html);
		System.out.println(doc);
		System.out.println("articles:" + getArticles(doc));
	}
	
	private static Document getArticle(Element articleUrl) {
		Node node = articleUrl.childNodes().get(0);
		String url = node.attr("href");
		return CrawlerUtil.getHtml(url);
	}
	
	private static Elements getArticles(Document doc) {
		return doc.getElementsByClass("readAll");
	}
}
