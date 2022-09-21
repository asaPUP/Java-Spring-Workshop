package com.uabc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uabc.entities.FilmActor;
import com.uabc.entities.FilmActorId;

public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId>{
    // TODO: add queries
}
