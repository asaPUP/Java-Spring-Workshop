package com.uabc.entities;

import java.io.Serial;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name = "store")
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id")
	private Integer storeId;

	@Column(name = "manager_staff_id")
	private Integer managerstaffId;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address addressId;

	@Column(name="last_update")
	@DateTimeFormat(pattern ="MM/dd/yyyy")
	private Date lastUpdate;

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getManagerstaffId() {
		return managerstaffId;
	}

	public void setManagerstaffId(Integer managerstaffId) {
		this.managerstaffId = managerstaffId;
	}

	public Address getAddressId() {
		return addressId;
	}

	public void setAddressId(Address addressId) {
		this.addressId = addressId;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Store(Integer storeId, Integer managerstaffId, Address addressId, Date lastUpdate) {
		super();
		this.storeId = storeId;
		this.managerstaffId = managerstaffId;
		this.addressId = addressId;
		this.lastUpdate = lastUpdate;
	}

	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Store [storeId=");
		builder.append(storeId);
		builder.append(", managerstaffId=");
		builder.append(managerstaffId);
		builder.append(", addressId=");
		builder.append(addressId);
		builder.append(", lastUpdate=");
		builder.append(lastUpdate);
		builder.append("]");
		return builder.toString();
	}





}
