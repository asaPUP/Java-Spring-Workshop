package com.uabc.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@NamedNativeQuery(name = "Film.obtenerPeliculas", query = "select f.film_id , f.title , (select c.name as category  from category c, film_category fc where c.category_id=fc.category_id  and fc.film_id=f.film_id limit 1) ,  (select count(*) as copies from inventory i where i.film_id=f.film_id group by i.film_id)  from film f", resultSetMapping = "Mapping.CatalogoIndex")
@SqlResultSetMapping(name = "Mapping.CatalogoIndex", classes = @ConstructorResult(targetClass = CatalogoIndex.class, columns = {
		@ColumnResult(name = "film_id", type = Integer.class), @ColumnResult(name = "title", type = String.class),
		@ColumnResult(name = "category", type = String.class), @ColumnResult(name = "copies", type = Integer.class) }))

@NamedNativeQuery(name = "Film.filtrarPeliculasTitulo", query = "select f.film_id , f.title , (select c.\"name\" as category  from category c, film_category fc where c.category_id=fc.category_id  and fc.film_id=f.film_id limit 1) ,  (select count(*) as copies from inventory i where i.film_id=f.film_id group by i.film_id)  from film f where UPPER(f.title) like (?)", resultSetMapping = "Mapping.FiltrarTitulo")
@SqlResultSetMapping(name = "Mapping.FiltrarTitulo", classes = @ConstructorResult(targetClass = CatalogoIndex.class, columns = {
		@ColumnResult(name = "film_id", type = Integer.class), @ColumnResult(name = "title", type = String.class),
		@ColumnResult(name = "category", type = String.class), @ColumnResult(name = "copies", type = Integer.class) }))

@NamedNativeQuery(name = "Film.filtrarPeliculasCategoria", query = "select * from (select f.film_id , f.title , (select c.\"name\" from category c, film_category fc where c.category_id=fc.category_id  and fc.film_id=f.film_id limit 1) as category ,  (select count(*) as copies from inventory i where i.film_id=f.film_id group by i.film_id)  from film f) as catalogo where catalogo.category=?", resultSetMapping = "Mapping.FiltrarCategoria")
@SqlResultSetMapping(name = "Mapping.FiltrarCategoria", classes = @ConstructorResult(targetClass = CatalogoIndex.class, columns = {
		@ColumnResult(name = "film_id", type = Integer.class), @ColumnResult(name = "title", type = String.class),
		@ColumnResult(name = "category", type = String.class), @ColumnResult(name = "copies", type = Integer.class) }))

@NamedNativeQuery(name = "Film.filtrarPeliculasActor", query = "select f.film_id, f.title, (select c.\"name\" as category  from category c, film_category fc where c.category_id=fc.category_id  and fc.film_id=f.film_id limit 1), (select count(*) as copies from inventory i where i.film_id=f.film_id group by i.film_id) from film f, film_actor fa, actor a where UPPER(concat(a.first_name,' ', a.last_name)) like(?) and f.film_id=fa.film_id and a.actor_id=fa.actor_id", resultSetMapping = "Mapping.FiltrarActor")
@SqlResultSetMapping(name = "Mapping.FiltrarActor", classes = @ConstructorResult(targetClass = CatalogoIndex.class, columns = {
		@ColumnResult(name = "film_id", type = Integer.class), @ColumnResult(name = "title", type = String.class),
		@ColumnResult(name = "category", type = String.class), @ColumnResult(name = "copies", type = Integer.class) }))

@Entity
@Table(name = "film")
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "film_id")
	private Integer filmId;
	 
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;

	@Column(name = "rental_duration")
	private Short rentalDuration;

	@Column(name = "rental_rate", precision = 4, scale = 2, columnDefinition = "numeric(4,2)")
	private Float rentalRate;

	private Short length;

	@Column(name = "replacement_cost", precision = 5, scale = 2, columnDefinition = "numeric(5,2)")
	private Integer replacementCost;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private java.sql.Timestamp last_update;

	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;
	
	public Integer getFilmId() {
		return filmId;
	}

	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(Short rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public Float getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(Float rentalRate) {
		this.rentalRate = rentalRate;
	}

	public Short getLength() {
		return length;
	}

	public void setLength(Short length) {
		this.length = length;
	}

	public Integer getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(Integer replacementCost) {
		this.replacementCost = replacementCost;
	}

	public java.sql.Timestamp getLast_update() {
		return last_update;
	}

	public void setLast_update(java.sql.Timestamp last_update) {
		this.last_update = last_update;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Film(Integer filmId, String title, String description,  Short rentalDuration,
			Float rentalRate, Short length, Integer replacementCost, Timestamp last_update, Language language,
			String rating) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.last_update = last_update;
		this.language = language;
	}

	public Film() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", description=" + description + ", rentalDuration="
				+ rentalDuration + ", rentalRate=" + rentalRate + ", length=" + length + ", replacementCost="
				+ replacementCost + ", last_update=" + last_update + ", language=" + language + "]";
	}

}
