package com.dbs.weather.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.dbs.weather.dao.impl.WeatherDaoImpl;
import com.dbs.weather.entity.WeatherDetails;
import com.dbs.weather.model.City;
import com.dbs.weather.model.FollowingDayForecast;
import com.dbs.weather.model.WeatherDetailsVo;
import com.dbs.weather.service.impl.WeatherServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = com.dbs.weather.WeatherApplication.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WeatherServiceTest {
	@Mock
	private ServletContext servletContext;

	@InjectMocks
	private WeatherServiceImpl weatherServiceImpl;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private WeatherDaoImpl weatherDao;

	@Before
	public void setUp() {
		List<City> citiesList = new ArrayList<>();
		citiesList.add(new City("Campbell", "CA", "37.2805754", "-121.9729761"));
		citiesList.add(new City("Omaha", "NE", "41.292032", "-96.2213323"));
		when(servletContext.getAttribute("citiesList")).thenReturn(citiesList);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetWeatherDetails() {
		ResponseEntity<Object> response = mock(ResponseEntity.class);
		WeatherDetailsVo weatherDetails = mock(WeatherDetailsVo.class);
		WeatherDetails weatherDetail = mock(WeatherDetails.class);
		when(weatherDao.getWeatherDetails("Campbell")).thenReturn(weatherDetail);
		when(restTemplate.exchange(Mockito.anyString(), Mockito.<HttpMethod>eq(HttpMethod.GET),
				Mockito.<HttpEntity<?>>any(), Mockito.<Class<Object>>any())).thenReturn(response);
		when(response.getBody()).thenReturn(weatherDetails);
		Set<String> cityNames = weatherServiceImpl.getWeatherDetails().keySet();
		assertEquals(2, cityNames.size());
	}

	@Test
	public void testGetFollowingDayForecasts() {
		LinkedHashMap<String, Object> data = new LinkedHashMap<>();
		data.put("summary", "Partly cloudy throughout the day.");
		data.put("time", 1566284400);
		data.put("temperatureHigh", 76.09);
		data.put("temperatureLow", 56.35);
		ArrayList<LinkedHashMap<String, Object>> dataList = new ArrayList<>();
		dataList.add(data);
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		map.put("data", dataList);
		WeatherDetailsVo weatherDetailsVo = new WeatherDetailsVo();
		weatherDetailsVo.setDaily(map);
		List<FollowingDayForecast> followingDayForecasts = weatherServiceImpl
				.getFollowingDayForecasts(weatherDetailsVo);
		assertEquals(76.09, followingDayForecasts.get(0).getMaxTemperature());
		assertEquals(56.35, followingDayForecasts.get(0).getMinTemperature());
	}

	@Test
	public void testDeleteOldForecasts() {
		when(weatherDao.deleteOldForecasts(LocalDate.now().minusDays(3))).thenReturn(3);
		assertEquals(3, weatherServiceImpl.deleteOldForecasts());
	}
}
