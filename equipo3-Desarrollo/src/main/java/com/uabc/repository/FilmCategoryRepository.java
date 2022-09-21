package com.uabc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uabc.entities.FilmCategory;
import com.uabc.entities.FilmCategoryId;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategoryId> {
    // TODO: add queries
}
