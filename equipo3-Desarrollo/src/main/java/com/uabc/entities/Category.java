package com.uabc.entities;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	//@NotNull
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private java.sql.Timestamp lastUpdate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.sql.Timestamp getLast_update() {
		return lastUpdate;
	}

	public void setLast_update(java.sql.Timestamp last_update) {
		this.lastUpdate = last_update;
	}

	public Category(Integer id, String name, Timestamp lastUpdate) {
		super();
		this.id = id;
		this.name = name;
		this.lastUpdate = lastUpdate;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", lastUpdate=");
		builder.append(lastUpdate);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, lastUpdate, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id) && Objects.equals(lastUpdate, other.lastUpdate)
				&& Objects.equals(name, other.name);
	}

}