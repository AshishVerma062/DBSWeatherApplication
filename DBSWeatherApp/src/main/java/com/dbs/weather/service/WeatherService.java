package com.dbs.weather.service;

import java.util.List;
import java.util.Map;

import com.dbs.weather.model.FollowingDayForecast;
import com.dbs.weather.model.WeatherDetailsVo;

public interface WeatherService {

	Map<String, WeatherDetailsVo> getWeatherDetails();

	List<FollowingDayForecast> getFollowingDayForecasts(WeatherDetailsVo weatherDetail);
	
	int deleteOldForecasts();
}
