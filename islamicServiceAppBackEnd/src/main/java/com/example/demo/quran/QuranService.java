package com.example.demo.quran;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuranService {
	private final RestTemplate restTemplate;
	private final QuranConfig quranConfig;
	
	@Autowired
	public QuranService(RestTemplate restTemplate, QuranConfig quranConfig) {
		this.restTemplate = restTemplate;
		this.quranConfig = quranConfig;
	}
	
	
	
	public Quran getSurah(Integer num) {
		String Surah="";
		String apiUrl=String.format("%s/surah/%d/%s",quranConfig.getUrl(),num,quranConfig.getVersion());
		String response= restTemplate.getForObject(apiUrl,String.class);
		Quran quran = new Quran();
		try{
			ObjectMapper objectMapper= new ObjectMapper();
			JsonNode rootNode =objectMapper.readTree(response);
			JsonNode dataNode=rootNode.path("data");
			quran.setName(dataNode.path("name").asText());
			quran.setRevelationType(dataNode.path("revelationType").asText());
			JsonNode ayahsNode =dataNode.path("ayahs");
			for(JsonNode ayahNode :ayahsNode){
				JsonNode textNode=ayahNode.path("text");
				Surah+=textNode.asText()+"۞";
			}
			quran.setText(Surah);
		}
			catch (Exception e) {
			throw new RuntimeException("Failed to parse surah data from API response", e);
		}
			return  quran;
	}
	
	public Quran getRandomAyah() {
		String response =restTemplate.getForObject(quranConfig.getAyahUrl(),String.class);
		Quran quran = new Quran();
		try{
			ObjectMapper objectMapper =new ObjectMapper();
			JsonNode rootNode=objectMapper.readTree(response);
			JsonNode verseNode=rootNode.path("verse");
			quran.setText(verseNode.path("text_uthmani").asText());
			
			String verseKey=verseNode.path("verse_key").asText();
			String url=String.format("%s/ayah/%s/%s",quranConfig.getUrl(),verseKey,quranConfig.getVersion());
			String response2 =restTemplate.getForObject(url,String.class);
			JsonNode rootNode2= objectMapper.readTree(response2);
			JsonNode dataNode=rootNode2.path("data");
			JsonNode surahNode=dataNode.path("surah");
			quran.setName(surahNode.path("name").asText());
			quran.setRevelationType(surahNode.path("revelationType").asText());
		}
		catch (Exception e) {
		throw new RuntimeException("Failed to parse surah data from API response", e);
		}
			return  quran;
	}
}
