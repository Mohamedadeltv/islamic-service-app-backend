package com.example.demo.Prayer;

import com.example.demo.Geolocation.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PrayerTimeService {
	
	private final RestTemplate restTemplate;
	
	private final PrayerTimeConfig prayerTimesConfig;
	
	private final GeolocationService geolocationService;
	@Autowired
	public PrayerTimeService(RestTemplate restTemplate, PrayerTimeConfig prayerTimesConfig, GeolocationService geolocationService) {
		this.restTemplate = restTemplate;
		this.prayerTimesConfig = prayerTimesConfig;
		this.geolocationService = geolocationService;
	}
	
	public PrayerTimeResponse.Data.Timings getPrayerTimes(String city, String country, String date, int method) {
			if(date==null || date.isEmpty()){
				date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			}
			if(city==null || city.isEmpty()){
				city=geolocationService.getCity();
			}
			if(country==null || country.isEmpty()){
				country=geolocationService.getCountry();
			}
		
			String url = String.format("%s/%s?city=%s&country=%s&method=%d",
					prayerTimesConfig.getUrl(), date, city, country, method);
			
			ResponseEntity<PrayerTimeResponse> responseEntity = restTemplate.getForEntity(url, PrayerTimeResponse.class);
			PrayerTimeResponse response = responseEntity.getBody();
			
			if (response == null || response.getData() == null || response.getData().getTimings() == null) {
				throw new RuntimeException("No prayer times found for the given location and date.");
			}
			
			return response.getData().getTimings();
	}
}