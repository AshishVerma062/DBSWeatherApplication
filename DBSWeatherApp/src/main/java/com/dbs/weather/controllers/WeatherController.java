package com.dbs.weather.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dbs.weather.model.FollowingDayForecast;
import com.dbs.weather.model.WeatherDetailsVo;
import com.dbs.weather.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

	private static final Logger logger = LogManager.getLogger(WeatherController.class);

	@Autowired
	private WeatherService weatherService;

	private Map<String, WeatherDetailsVo> weatherDetails = new LinkedHashMap<>();

	@GetMapping("/details")
	public ModelAndView getWeatherDetails() {
		logger.info("WeatherController --> fetching weather details for all the cities.");
		weatherDetails = weatherService.getWeatherDetails();
		ModelAndView model = new ModelAndView();
		model.addObject("cities", weatherDetails.keySet());
		WeatherDetailsVo weatherDetail = weatherDetails.entrySet().stream().findFirst().get().getValue();
		List<FollowingDayForecast> followingDayForecasts = weatherService.getFollowingDayForecasts(weatherDetail);
		model.addObject("weatherDetail", weatherDetail);
		model.addObject("followingDayForecasts", followingDayForecasts);
		model.setViewName("weatherDetails");
		logger.info("WeatherController --> weather details fetched, values are set in model, view name is returned.");
		return model;
	}

	@GetMapping("/details/{cityName}")
	public ModelAndView getWeatherDetailsForSelectedCity(@PathVariable String cityName) {
		logger.info("WeatherController --> fetching weather details for the selected city.");
		if (weatherDetails == null || weatherDetails.size() == 0) {
			weatherDetails = weatherService.getWeatherDetails();
		}
		WeatherDetailsVo weatherDetail = weatherDetails.entrySet().stream()
				.filter(entry -> entry.getKey().equalsIgnoreCase(cityName)).findFirst().get().getValue();
		List<FollowingDayForecast> followingDayForecasts = weatherService.getFollowingDayForecasts(weatherDetail);
		ModelAndView model = new ModelAndView();
		model.addObject("cities", weatherDetails.keySet());
		model.addObject("selectedCity", cityName);
		model.addObject("weatherDetail", weatherDetail);
		model.addObject("followingDayForecasts", followingDayForecasts);
		model.setViewName("weatherDetails");
		logger.info("WeatherController --> weather details fetched, values are set in model, view name is returned.");
		return model;
	}

	@GetMapping("/deleteOldForecasts")
	public ModelAndView deleteForecasts() {
		logger.info("WeatherController --> delete forecasts which are 3 days old.");
		int deletedForecasts = weatherService.deleteOldForecasts();
		ModelAndView model = new ModelAndView();
		if (deletedForecasts > 0) {
			model.addObject("success", deletedForecasts + " Old forecast(s) deleted successfully!!!");
		} else {
			model.addObject("success", "No old forecast(s) exist(s)!!!");
		}
		model.setViewName("index");
		logger.info("WeatherController --> forecasts deleted successfully.");
		logger.info("WeatherController --> redirecting to index page.");
		return model;
	}

	@GetMapping("/")
	public ModelAndView getIndexPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		logger.info("WeatherController --> redirecting to index page.");
		return model;
	}

}
