package com.uabc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uabc.entities.FilmActor;
import com.uabc.entities.FilmCategory;
import com.uabc.repository.FilmActorRepository;
import com.uabc.repository.FilmCategoryRepository;

@Service
public class FilmCategoryService {
	
	@Autowired
	private FilmCategoryRepository categoryRepository;
	
	public FilmCategory insertFilmCategory(FilmCategory filmActor) 
	{
		return categoryRepository.save(filmActor);
	}

}
