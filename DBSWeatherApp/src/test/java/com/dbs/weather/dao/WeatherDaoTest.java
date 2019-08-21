package com.dbs.weather.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.dbs.weather.dao.impl.WeatherDaoImpl;
import com.dbs.weather.entity.WeatherDetails;
import com.dbs.weather.repository.WeatherRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = com.dbs.weather.WeatherApplication.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WeatherDaoTest {

	@InjectMocks
	private WeatherDaoImpl weatherDaoImpl;

	@Mock
	private WeatherRepository weatherRepository;

	@Test
	public void testAddWeatherDetails() {
		WeatherDetails weatherDetails = mock(WeatherDetails.class);
		when(weatherRepository.save(weatherDetails)).thenReturn(weatherDetails);
		assertEquals(weatherDetails, weatherDaoImpl.addWeatherDetails(weatherDetails));
	}

	@Test
	public void testGetWeatherDetails() {
		WeatherDetails weatherDetails = mock(WeatherDetails.class);
		when(weatherRepository.findByCityNameAndDate("Campbell", LocalDate.now())).thenReturn(weatherDetails);
		assertEquals(weatherDetails, weatherDaoImpl.getWeatherDetails("Campbell"));
	}

	@Test
	public void testDeleteOldForecasts() {
		List<WeatherDetails> list = new ArrayList<>();
		list.add(new WeatherDetails());
		list.add(new WeatherDetails());
		when(weatherRepository.deleteByDateLessThanEqual(LocalDate.now())).thenReturn(list);
		assertEquals(2, weatherDaoImpl.deleteOldForecasts(LocalDate.now()));
	}
}
