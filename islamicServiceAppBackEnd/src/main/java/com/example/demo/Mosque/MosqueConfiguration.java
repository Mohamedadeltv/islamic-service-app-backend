package com.example.demo.Mosque;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "geoapify.api")
public class MosqueConfiguration {
	private String url;
	private String key;
	private String Categories;
	private String radius;
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getCategories() {
		return Categories;
	}
	
	public void setCategories(String categories) {
		Categories = categories;
	}
	
	public String getRadius() {
		return radius;
	}
	
	public void setRadius(String radius) {
		this.radius = radius;
	}
}
