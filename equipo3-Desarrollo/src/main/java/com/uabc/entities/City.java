package com.uabc.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name = "city")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	private Integer cityId;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "country_id")
	private Integer countryId;
	
	@Column(name="last_update")
	@DateTimeFormat(pattern ="MM/dd/yyyy")
	private Date lastUpdate;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public City(Integer cityId, String city, Integer countryId, Date lastUpdate) {
		super();
		this.cityId = cityId;
		this.city = city;
		this.countryId = countryId;
		this.lastUpdate = lastUpdate;
	}

	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("City [cityId=");
		builder.append(cityId);
		builder.append(", city=");
		builder.append(city);
		builder.append(", countryId=");
		builder.append(countryId);
		builder.append(", lastUpdate=");
		builder.append(lastUpdate);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}
