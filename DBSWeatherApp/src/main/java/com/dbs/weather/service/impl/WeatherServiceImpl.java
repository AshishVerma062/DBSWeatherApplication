package com.dbs.weather.service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.dbs.weather.dao.WeatherDao;
import com.dbs.weather.entity.WeatherDetails;
import com.dbs.weather.exception.ApplicationException;
import com.dbs.weather.model.City;
import com.dbs.weather.model.FollowingDayForecast;
import com.dbs.weather.model.WeatherDetailsVo;
import com.dbs.weather.model.WeatherObjectsMapper;
import com.dbs.weather.service.WeatherService;

@Service
@Transactional(rollbackFor = Exception.class)
public class WeatherServiceImpl implements WeatherService {

	private static final Logger logger = LogManager.getLogger(WeatherServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private WeatherDao weatherDao;

	@Value("${darksky.api.url}")
	private String darkskyApiUrl;

	@Override
	public Map<String, WeatherDetailsVo> getWeatherDetails() {
		logger.info("WeatherServiceImpl --> inside service method to fetching weather details for all the cities.");
		List<City> citiesList = (List<City>) servletContext.getAttribute("citiesList");
		Map<String, WeatherDetailsVo> citiesWeatherDetails = new LinkedHashMap<>();
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<String>(httpHeaders);
		for (City city : citiesList) {
			try {
				logger.info("WeatherServiceImpl --> Fetching details from DB for city ::: " + city.getName());
				WeatherDetails weatherDetailsFromDB = weatherDao.getWeatherDetails(city.getName());
				if (weatherDetailsFromDB != null) {
					logger.info("WeatherServiceImpl --> Details exists in DB for city ::: " + city.getName());
					WeatherDetailsVo detailsVo = WeatherObjectsMapper.getMappedWeatherDetailsVo(weatherDetailsFromDB);
					citiesWeatherDetails.put(city.getName(), detailsVo);
				} else {
					logger.info("WeatherServiceImpl --> Details doesn't exist in DB for city ::: " + city.getName());
					logger.info("WeatherServiceImpl --> Fetching details for city ::: " + city.getName());
					logger.info("WeatherServiceImpl --> Api call to darksky ::: " + darkskyApiUrl + city.getLattitude()
							+ "," + city.getLongitude());
					ResponseEntity<WeatherDetailsVo> responseEntity = restTemplate.exchange(
							darkskyApiUrl + city.getLattitude() + "," + city.getLongitude(), HttpMethod.GET,
							requestEntity, WeatherDetailsVo.class);
					WeatherDetailsVo detailsVo = responseEntity.getBody();
					citiesWeatherDetails.put(city.getName(), detailsVo);
					WeatherDetails weatherDetails = WeatherObjectsMapper.getMappedWeatherDetails(detailsVo,
							city.getName());
					weatherDao.addWeatherDetails(weatherDetails);
				}
			} catch (Exception ex) {
				logger.error("WeatherServiceImpl --> Error occurred while fetching the details for city ::: "
						+ city.getName());
			}

		}
		logger.info(
				"WeatherServiceImpl --> weather details fetched, returning the response to the controller. size ::: "
						+ citiesWeatherDetails.size());
		return citiesWeatherDetails;
	}

	@Override
	public List<FollowingDayForecast> getFollowingDayForecasts(WeatherDetailsVo weatherDetail) {
		logger.info("WeatherServiceImpl --> inside service method to get next days forecast of a city.");
		List<FollowingDayForecast> list = new ArrayList<>();
		LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) weatherDetail.getDaily();
		ArrayList<LinkedHashMap<String, Object>> data = (ArrayList<LinkedHashMap<String, Object>>) map.get("data");
		for (LinkedHashMap<String, Object> each : data) {
			Object summary = each.get("summary");
			int time = (int) each.get("time");
			Object maxTemp = each.get("temperatureHigh");
			Object minTemp = each.get("temperatureLow");
			FollowingDayForecast forecast = new FollowingDayForecast();
			forecast.setSummary(summary);
			forecast.setMinTemperature(minTemp);
			forecast.setMaxTemperature(maxTemp);
			LocalDate date = Instant.ofEpochSecond(time).atZone(ZoneId.systemDefault()).toLocalDate();
			forecast.setDate(date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
			forecast.setDay(date.getDayOfWeek().name());
			list.add(forecast);
		}
		logger.info(
				"WeatherServiceImpl --> forecast details fetched, returning the response to the controller. size ::: "
						+ list.size());
		return list;
	}

	@Override
	public int deleteOldForecasts() {
		logger.info("WeatherServiceImpl --> delete forecasts which are 3 days old.");
		LocalDate dateThreeDaysBefore = LocalDate.now().minusDays(3);
		logger.info("WeatherServiceImpl --> dateThreeDaysBefore ::: " + dateThreeDaysBefore);
		try {
			return weatherDao.deleteOldForecasts(dateThreeDaysBefore);
		} catch (Exception ex) {
			logger.error("WeatherServiceImpl --> error while deleting forecasts.");
			throw new ApplicationException(ex.getMessage());
		}
	}

}
