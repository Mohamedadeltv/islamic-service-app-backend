package com.example.demo.Mosque;

import com.example.demo.Geolocation.GeolocationConfig;
import com.example.demo.Geolocation.GeolocationService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MosqueService {
	private final RestTemplate restTemplate;
	private final MosqueConfiguration mosqueConfiguration;
	private final GeolocationService geolocationService;
	
	@Autowired
	public MosqueService(RestTemplate restTemplate, MosqueConfiguration mosqueConfiguration, GeolocationService geolocationService) {
		this.restTemplate = restTemplate;
		this.mosqueConfiguration = mosqueConfiguration;
		this.geolocationService= geolocationService;
	}
	public String swapCoordinates(){
		String coordinate= geolocationService.getLoc();
		String[] parts = coordinate.split(",");
		String swappedCoordinates = parts[1] + "," + parts[0];
		return  swappedCoordinates;
	}
	
	public List<Mosque> getNearestMosques() {
		String apiUrl=String.format("%s?categories=%s&filter=circle:%s,%s&limit=50&apiKey=%s",
				mosqueConfiguration.getUrl(),
				mosqueConfiguration.getCategories(),
				swapCoordinates(),
				mosqueConfiguration.getRadius(),
				mosqueConfiguration.getKey()
				);
		
		String response =restTemplate.getForObject(apiUrl,String.class);
		List<Mosque> mosques = new ArrayList<>();
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(response);
			// Extract the "features" array from the JSON
			JsonNode featuresNode = rootNode.path("features");
			
			for(JsonNode featureNode : featuresNode){
				JsonNode propertiesNode = featureNode.path("properties");
				Mosque mosque = new Mosque();
				mosque.setName(propertiesNode.path("name").asText());
				mosque.setDistrict(propertiesNode.path("district").asText());
				mosque.setStreet(propertiesNode.path("street").asText());
				mosques.add(mosque);
			}
			
		}
		catch (Exception e) {
			throw new RuntimeException("Failed to parse mosque data from API response", e);
		}
	return mosques;
	}
}
