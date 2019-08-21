package com.dbs.weather.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import com.dbs.weather.model.City;

@Configuration
public class DefaultValuesProvider implements ServletContextAware {

	private static final Logger logger = LogManager.getLogger(DefaultValuesProvider.class);

	@Override
	public void setServletContext(ServletContext servletContext) {
		logger.info("DefaultValuesProvider --> setting default values for cities.");
		List<City> citiesList = new ArrayList<>();
		citiesList.add(new City("Austin", "TX", "30.3079827", "-97.8934863"));
		citiesList.add(new City("Campbell", "CA", "37.2805754", "-121.9729761"));
		citiesList.add(new City("Jakarta", "Indonesia", "-6.2293867", "106.6894322"));
		citiesList.add(new City("Nara", "Japan", "34.6580727", "135.8220505"));
		citiesList.add(new City("Niseko", "Japan", "42.7927286", "140.6145001"));
		citiesList.add(new City("Omaha", "NE", "41.292032", "-96.2213323"));
		servletContext.setAttribute("citiesList", citiesList);
	}

}
