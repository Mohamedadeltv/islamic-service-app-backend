package com.example.demo.Prayer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrayerTimeController {
	
	private final PrayerTimeService prayerTimeService;
	
	@Autowired
	public PrayerTimeController(PrayerTimeService prayerTimeService) {
		this.prayerTimeService = prayerTimeService;
	}
	
	@GetMapping("/prayer-times")
	public PrayerTimeResponse.Data.Timings getPrayerTimings(
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String country,
			@RequestParam(required = false)  String date,
			@RequestParam @NotNull(message = "method required") int method){
		return prayerTimeService.getPrayerTimes(city,country,date,method);
		
	}
	
}
