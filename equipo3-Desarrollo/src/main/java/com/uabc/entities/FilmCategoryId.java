package com.uabc.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FilmCategoryId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "film_id")
	private Integer filmId;
	
	@Column(name = "category_id")
    private Integer categoryId;

    
    public Integer getFilmId() {
		return filmId;
	}

	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FilmCategoryId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilmCategoryId(Integer filmId, Integer categoryId) {
        this.filmId = filmId;
        this.categoryId = categoryId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmCategoryId filmCategoryId = (FilmCategoryId) o;
        return filmId.equals(filmCategoryId.filmId) &&
                categoryId.equals(filmCategoryId.categoryId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(filmId, categoryId);
    }
}
