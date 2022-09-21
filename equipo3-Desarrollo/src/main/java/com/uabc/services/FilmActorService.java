package com.uabc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uabc.entities.FilmActor;
import com.uabc.repository.FilmActorRepository;

@Service
public class FilmActorService {

	@Autowired
	private FilmActorRepository actorRepository;
	
	public FilmActor insertFilmActor(FilmActor filmActor) 
	{
		return actorRepository.save(filmActor);
	}
}
