package com.vurtnec.component.crawler;

import java.util.HashMap;
import java.util.Map;

public class CrawlerConfiguration {

	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getMap() {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("vurtnecTitle", "Personal Essay");
		map.put("vurtnecSubTitle", "for my own");
		map.put("vurtnecAuthor", "Vurtnec");
		map.put("vurtnecCategory", "1");
		map.put("vurtnecImage", "img/home-bg.png");
		
		return map;
	}
}
