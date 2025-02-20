package com.example.demo.quran;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "quran.api")
public class QuranConfig {
	private String url;
	private String	version;
	
	@Value("${randomAyah.api.url}")
	private String ayahUrl;
	
	public String getAyahUrl() {
		return ayahUrl;
	}
	
	public void setAyahUrl(String ayahUrl) {
		this.ayahUrl = ayahUrl;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
}
