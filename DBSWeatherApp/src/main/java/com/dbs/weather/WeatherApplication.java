package com.dbs.weather;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WeatherApplication {

	private static final Logger logger = LogManager.getLogger(WeatherApplication.class);

	public static void main(String[] args) {
		logger.info("WeatherApplication --> starting the application.");
		SpringApplication.run(WeatherApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		logger.info("WeatherApplication --> creating  RestTemplate bean.");
		return new RestTemplate();
	}

}
