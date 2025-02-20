package com.example.demo.Prayer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PrayerTimeResponse {
	private Data data;
	
	@lombok.Data
	public static class Data {
		private Timings timings;
		
		@lombok.Data
		public static class Timings {
			@JsonProperty("Fajr")
			private String fajr;
			
			@JsonProperty("Dhuhr")
			private String dhuhr;
			
			@JsonProperty("Asr")
			private String asr;
			
			@JsonProperty("Maghrib")
			private String maghrib;
			
			@JsonProperty("Isha")
			private String isha;
		}
	}
}