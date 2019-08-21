package com.dbs.weather.model;

import java.time.LocalDate;
import java.util.UUID;

import com.dbs.weather.entity.WeatherDetails;

public class WeatherObjectsMapper {

	public static WeatherDetails getMappedWeatherDetails(WeatherDetailsVo weatherDetailsVo, String cityName) {
		WeatherDetails weatherDetails = new WeatherDetails();
		weatherDetails.setId(UUID.randomUUID().toString());
		weatherDetails.setLatitude(weatherDetailsVo.getLatitude());
		weatherDetails.setLongitude(weatherDetailsVo.getLongitude());
		weatherDetails.setTimezone(weatherDetailsVo.getTimezone());
		weatherDetails.setCurrently(weatherDetailsVo.getCurrently());
		weatherDetails.setMinutely(weatherDetailsVo.getMinutely());
		weatherDetails.setHourly(weatherDetailsVo.getHourly());
		weatherDetails.setDaily(weatherDetailsVo.getDaily());
		weatherDetails.setFlags(weatherDetailsVo.getFlags());
		weatherDetails.setOffset(weatherDetailsVo.getOffset());
		weatherDetails.setDate(LocalDate.now());
		weatherDetails.setCityName(cityName);
		return weatherDetails;
	}

	public static WeatherDetailsVo getMappedWeatherDetailsVo(WeatherDetails weatherDetails) {
		WeatherDetailsVo weatherDetailsVo = new WeatherDetailsVo();
		weatherDetailsVo.setLatitude(weatherDetails.getLatitude());
		weatherDetailsVo.setLongitude(weatherDetails.getLongitude());
		weatherDetailsVo.setTimezone(weatherDetails.getTimezone());
		weatherDetailsVo.setCurrently(weatherDetails.getCurrently());
		weatherDetailsVo.setMinutely(weatherDetails.getMinutely());
		weatherDetailsVo.setHourly(weatherDetails.getHourly());
		weatherDetailsVo.setDaily(weatherDetails.getDaily());
		weatherDetailsVo.setFlags(weatherDetails.getFlags());
		weatherDetailsVo.setOffset(weatherDetails.getOffset());
		return weatherDetailsVo;
	}

}