package com.dbs.weather.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dbs.weather.model.FollowingDayForecast;
import com.dbs.weather.model.WeatherDetailsVo;
import com.dbs.weather.service.impl.WeatherServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = com.dbs.weather.WeatherApplication.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WeatherControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private WeatherController weatherController;

	@Mock
	private WeatherServiceImpl weatherService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
	}

	@Test
	public void testGetWeatherDetails() throws Exception {
		when(weatherService.getWeatherDetails()).thenReturn(getDummyWeatherDetails());
		mockMvc.perform(get("/weather/details").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testDeleteForecasts() throws Exception {
		when(weatherService.deleteOldForecasts()).thenReturn(2);
		mockMvc.perform(get("/weather/deleteOldForecasts").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteForecastsNoDeletion() throws Exception {
		when(weatherService.deleteOldForecasts()).thenReturn(0);
		mockMvc.perform(get("/weather/deleteOldForecasts").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetWeatherDetailsForSelectedCity() throws Exception {
		when(weatherService.getWeatherDetails()).thenReturn(getDummyWeatherDetails());
		List<FollowingDayForecast> followingDayForecasts = new ArrayList<FollowingDayForecast>();
		FollowingDayForecast followingDayForecast = new FollowingDayForecast();
		followingDayForecast.setDate("2019-08-21");
		followingDayForecast.setDay("WEDNESDAY");
		followingDayForecast.setMaxTemperature(45.65);
		followingDayForecast.setMinTemperature(25.65);
		followingDayForecast.setSummary("Cloudy");
		followingDayForecasts.add(followingDayForecast);
		when(weatherService.getFollowingDayForecasts(new WeatherDetailsVo())).thenReturn(followingDayForecasts);
		mockMvc.perform(get("/weather/details/Austin").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private Map<String, WeatherDetailsVo> getDummyWeatherDetails() {
		Map<String, WeatherDetailsVo> citiesWeatherDetails = new LinkedHashMap<>();
		citiesWeatherDetails.put("Austin", new WeatherDetailsVo());
		citiesWeatherDetails.put("Campbell", new WeatherDetailsVo());
		return citiesWeatherDetails;
	}

	@Test
	public void testGetIndexPage() throws Exception {
		mockMvc.perform(get("/weather/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
