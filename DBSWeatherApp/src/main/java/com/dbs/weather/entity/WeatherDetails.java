package com.dbs.weather.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "WEATHER_DETAILS")
public class WeatherDetails {

	@Id
	private String id;
	private String cityName;
	private double latitude;
	private double longitude;
	private String timezone;
	private Object currently;
	private Object minutely;
	private Object hourly;
	private Object daily;
	private Object flags;
	private int offset;
	private LocalDate date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Object getCurrently() {
		return currently;
	}

	public void setCurrently(Object currently) {
		this.currently = currently;
	}

	public Object getMinutely() {
		return minutely;
	}

	public void setMinutely(Object minutely) {
		this.minutely = minutely;
	}

	public Object getHourly() {
		return hourly;
	}

	public void setHourly(Object hourly) {
		this.hourly = hourly;
	}

	public Object getDaily() {
		return daily;
	}

	public void setDaily(Object daily) {
		this.daily = daily;
	}

	public Object getFlags() {
		return flags;
	}

	public void setFlags(Object flags) {
		this.flags = flags;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
