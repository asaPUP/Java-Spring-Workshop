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
@Table (name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Integer addressId;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "address2")
	private String address2;
	
	@Column(name = "district")
	private String district;

	@Column(name = "city_id")
	private Integer city_id;

	@Column(name = "postal_code")
	private String postal_code;

	@Column(name = "phone")
	private String phone;
	
	@Column(name="last_update")
	@DateTimeFormat(pattern ="MM/dd/yyyy")
	private Date lastUpdate;

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Address(Integer addressId, String address, String address2, String district, Integer city_id,
			String postal_code, String phone, Date lastUpdate) {
		super();
		this.addressId = addressId;
		this.address = address;
		this.address2 = address2;
		this.district = district;
		this.city_id = city_id;
		this.postal_code = postal_code;
		this.phone = phone;
		this.lastUpdate = lastUpdate;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [addressId=");
		builder.append(addressId);
		builder.append(", address=");
		builder.append(address);
		builder.append(", address2=");
		builder.append(address2);
		builder.append(", district=");
		builder.append(district);
		builder.append(", city_id=");
		builder.append(city_id);
		builder.append(", postal_code=");
		builder.append(postal_code);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", lastUpdate=");
		builder.append(lastUpdate);
		builder.append("]");
		return builder.toString();
	}
	
	
}
