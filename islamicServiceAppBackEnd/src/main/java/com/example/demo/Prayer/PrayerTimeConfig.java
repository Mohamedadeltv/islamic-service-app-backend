package com.example.demo.Prayer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties(prefix = "prayer.times.api")
public class PrayerTimeConfig {
	
	private String url;
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
