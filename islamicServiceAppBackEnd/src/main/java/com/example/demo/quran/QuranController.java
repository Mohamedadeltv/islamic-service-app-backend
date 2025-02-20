package com.example.demo.quran;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuranController {
	private QuranService quranService;
	
	@Autowired
	public QuranController(QuranService quranService) {
		this.quranService = quranService;
	}
	
	@GetMapping("/surah")
	public Quran getSurah(@RequestParam @NotNull Integer num){
		return quranService.getSurah(num);
	}
	@GetMapping("/random-ayah")
	public Quran getRandomAyah(){
		return quranService.getRandomAyah();
	}
	
}
