package com.dbs.weather.model;

public class FollowingDayForecast {
	private String day;
	private String date;
	private Object summary;
	private Object minTemperature;
	private Object maxTemperature;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Object getSummary() {
		return summary;
	}

	public void setSummary(Object summary) {
		this.summary = summary;
	}

	public Object getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(Object minTemperature) {
		this.minTemperature = minTemperature;
	}

	public Object getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(Object maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

}
