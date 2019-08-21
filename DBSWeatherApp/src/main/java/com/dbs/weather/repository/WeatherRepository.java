package com.dbs.weather.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dbs.weather.entity.WeatherDetails;

public interface WeatherRepository extends MongoRepository<WeatherDetails, String> {

	WeatherDetails findByCityNameAndDate(String cityName, LocalDate date);

	List<WeatherDetails> deleteByDateLessThanEqual(LocalDate date);
}
