package com.uabc.entities;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "language")
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "language_id")
	private Integer languageId;
	
	@Column(columnDefinition = "bpchar(20)", length = 20)
	private String name;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private java.sql.Timestamp last_update;

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.sql.Timestamp getLast_update() {
		return last_update;
	}

	public void setLast_update(java.sql.Timestamp last_update) {
		this.last_update = last_update;
	}

	@Override
	public int hashCode() {
		return Objects.hash(languageId, last_update, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		return Objects.equals(languageId, other.languageId) && Objects.equals(last_update, other.last_update)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Language [languageId=");
		builder.append(languageId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", last_update=");
		builder.append(last_update);
		builder.append("]");
		return builder.toString();
	}

	public Language(Integer languageId, String name, Timestamp last_update) {
		super();
		this.languageId = languageId;
		this.name = name;
		this.last_update = last_update;
	}

	public Language() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
