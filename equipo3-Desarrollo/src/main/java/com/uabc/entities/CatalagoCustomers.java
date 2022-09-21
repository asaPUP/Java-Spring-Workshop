package com.uabc.entities;

public class CatalagoCustomers {
	
	private Integer customer_id;
	private Integer store_id;
	private String first_name;
	private String last_name;
	private String email;
	
	public CatalagoCustomers(Integer customer_id, String first_name, String last_name, String email,Integer store_id) {
		super();
		this.customer_id = customer_id;
		this.store_id = store_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public CatalagoCustomers() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CatalagoCustomers [customer_id=");
		builder.append(customer_id);
		builder.append(", store_id=");
		builder.append(store_id);
		builder.append(", first_name=");
		builder.append(first_name);
		builder.append(", last_name=");
		builder.append(last_name);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
