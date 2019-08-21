package com.dbs.weather.dao;

import java.time.LocalDate;

import com.dbs.weather.entity.WeatherDetails;

public interface WeatherDao {

	WeatherDetails addWeatherDetails(WeatherDetails weatherDetails);

	WeatherDetails getWeatherDetails(String cityName);

	int deleteOldForecasts(LocalDate dateThreeDaysBefore);
}
