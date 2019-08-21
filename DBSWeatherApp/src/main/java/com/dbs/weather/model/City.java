package com.dbs.weather.model;

public class City {

	private String name;
	private String country;
	private String lattitude;
	private String longitude;

	public City(String name, String country, String lattitude, String longitude) {
		super();
		this.name = name;
		this.country = country;
		this.lattitude = lattitude;
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLattitude() {
		return lattitude;
	}

	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
