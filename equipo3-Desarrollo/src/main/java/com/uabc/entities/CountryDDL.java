package com.uabc.entities;

public class CountryDDL {
	
	private Integer id;
	
	private String country;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	public CountryDDL(Integer id, String country) {
		super();
		this.id = id;
		this.country = country;
	}

	public CountryDDL() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
