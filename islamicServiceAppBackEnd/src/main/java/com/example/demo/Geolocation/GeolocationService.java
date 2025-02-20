package com.example.demo.Geolocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeolocationService {
	
	private final RestTemplate restTemplate;
	private final GeolocationConfig geolocationConfig;
	
	@Autowired
	public GeolocationService(RestTemplate restTemplate, GeolocationConfig geolocationConfig) {
		this.restTemplate = restTemplate;
		this.geolocationConfig = geolocationConfig;
	}
	
	public String getCity(){
		GeolocationResponse response =restTemplate.getForObject(geolocationConfig.getUrl(),GeolocationResponse.class);
		return response.getCity();
	}
	
	public String getCountry(){
		GeolocationResponse response =restTemplate.getForObject(geolocationConfig.getUrl(),GeolocationResponse.class);
		return response.getCountry();
	}
	
	public String getLoc(){
		GeolocationResponse response =restTemplate.getForObject(geolocationConfig.getUrl(),GeolocationResponse.class);
		return response.getLoc();
	}
}
