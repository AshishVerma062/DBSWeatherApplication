package com.dbs.weather.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dbs.weather.dao.WeatherDao;
import com.dbs.weather.entity.WeatherDetails;
import com.dbs.weather.repository.WeatherRepository;

@Repository
public class WeatherDaoImpl implements WeatherDao {

	private static final Logger logger = LogManager.getLogger(WeatherDaoImpl.class);

	@Autowired
	private WeatherRepository weatherRepository;

	@Override
	public WeatherDetails addWeatherDetails(WeatherDetails weatherDetails) {
		logger.info("WeatherDaoImpl --> adding weather details.");
		return weatherRepository.save(weatherDetails);
	}

	@Override
	public WeatherDetails getWeatherDetails(String cityName) {
		logger.info("WeatherDaoImpl --> fetching weather details for a city.");
		return weatherRepository.findByCityNameAndDate(cityName, LocalDate.now());
	}

	@Override
	public int deleteOldForecasts(LocalDate dateThreeDaysBefore) {
		logger.info("WeatherDaoImpl --> delete forecasts which are 3 days old.");
		List<WeatherDetails> deletedForecasts = weatherRepository.deleteByDateLessThanEqual(dateThreeDaysBefore);
		logger.info("WeatherDaoImpl --> There are " + deletedForecasts.size() + " forecasts which are 3 days old.");
		return deletedForecasts.size();
	}
}
