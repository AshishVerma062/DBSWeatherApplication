package com.dbs.weather.model;

public class WeatherDetailsVo {
	private double latitude;
	private double longitude;
	private String timezone;
	private Object currently;
	private Object minutely;
	private Object hourly;
	private Object daily;
	private Object flags;
	private int offset;

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

}
