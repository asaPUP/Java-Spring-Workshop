package com.uabc.entities;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
@NamedNativeQuery(name = "Customer.obtenerCustomers", query = "select c.customer_id ,c.first_name, c.last_name, c.email, c.store_id  from customer c", resultSetMapping = "Mapping.CatalagoCustomers")
@SqlResultSetMapping(name = "Mapping.CatalagoCustomers", classes = @ConstructorResult(targetClass = CatalagoCustomers.class, columns = {
		@ColumnResult(name = "customer_id", type = Integer.class), @ColumnResult(name = "first_name", type = String.class),
		@ColumnResult(name = "last_name", type = String.class),@ColumnResult(name = "email", type = String.class),
		@ColumnResult(name = "store_id", type = Integer.class) }))

@Entity
@Table (name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Integer customerId;

	//store_id
	@Column(name = "store_id")
	private Integer storeId;
	
	//first_name
	@Column(name="first_name")
	private String firstName;
	
	//last_name
	@Column(name="last_name")
	private String lastName;
	
	@Column(unique = true)
	private String email;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@Column(name="activebool")
	private Boolean activebool;
	
	@Column(name="last_update")
	@DateTimeFormat(pattern ="MM/dd/yyyy")
	private Date lastUpdate;
	
	@Column(name="active")
	private Integer active;
	
	

	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Boolean getActivebool() {
		return activebool;
	}

	public void setActivebool(Boolean activebool) {
		this.activebool = activebool;
	}


	/*public Date getCreateDate() {
		return createDate;
	}*/


	/*public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}*/


	public Date getLastUpdate() {
		return lastUpdate;
	}


	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}


	public Customer(Integer customerId, Integer storeId, String firstName, String lastName, String email,
			Boolean activebool, Date createDate, Date lastUpdate, Integer active) {
		super();
		this.customerId = customerId;
		this.storeId = storeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		
		this.activebool = true;
		//this.createDate = createDate;
		this.lastUpdate = lastUpdate;
		this.active = active;
	}


	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [customerId=");
		builder.append(customerId);
		builder.append(", storeId=");
		builder.append(storeId);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", addressId=");
		builder.append(", activebool=");
		builder.append(activebool);
		builder.append(", createDate=");
		//builder.append(createDate);
		builder.append(", lastUpdate=");
		builder.append(lastUpdate);
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
	

}
