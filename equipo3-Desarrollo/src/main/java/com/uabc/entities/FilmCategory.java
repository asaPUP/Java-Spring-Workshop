package com.uabc.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "film_category")
public class FilmCategory implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private FilmCategoryId filmCategoryId;
    
    @Column(name="last_update")
	@DateTimeFormat(pattern ="MM/dd/yyyy")
	private Date lastUpdate;
    
    public FilmCategory() {
	}

	public FilmCategory(FilmCategoryId filmCategoryId, Date lastUpdate) {
		super();
		this.filmCategoryId = filmCategoryId;
		this.lastUpdate = lastUpdate;
	}

	public FilmCategoryId getFilmCategoryId() {
		return filmCategoryId;
	}

	public void setFilmCategoryId(FilmCategoryId filmCategoryId) {
		this.filmCategoryId = filmCategoryId;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(filmCategoryId, lastUpdate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmCategory other = (FilmCategory) obj;
		return Objects.equals(filmCategoryId, other.filmCategoryId) && Objects.equals(lastUpdate, other.lastUpdate);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FilmCategory [filmCategoryId=");
		builder.append(filmCategoryId);
		builder.append(", lastUpdate=");
		builder.append(lastUpdate);
		builder.append("]");
		return builder.toString();
	}

}
