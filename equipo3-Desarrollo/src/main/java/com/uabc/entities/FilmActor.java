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
@Table(name = "film_actor")
public class FilmActor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private FilmActorId filmActorId;
    
    @Column(name="last_update")
	@DateTimeFormat(pattern ="MM/dd/yyyy")
	private Date lastUpdate;
    
    public FilmActor() {
	}

	public FilmActor(FilmActorId filmActorId, Date lastUpdate) {
		super();
		this.filmActorId = filmActorId;
		this.lastUpdate = lastUpdate;
	}

	public FilmActorId getFilmActorId() {
		return filmActorId;
	}

	public void setFilmActorId(FilmActorId filmActorId) {
		this.filmActorId = filmActorId;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(filmActorId, lastUpdate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmActor other = (FilmActor) obj;
		return Objects.equals(filmActorId, other.filmActorId) && Objects.equals(lastUpdate, other.lastUpdate);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FilmActor [filmActorId=");
		builder.append(filmActorId);
		builder.append(", lastUpdate=");
		builder.append(lastUpdate);
		builder.append("]");
		return builder.toString();
	}
    
}
