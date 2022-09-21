package com.uabc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.uabc.entities.CatalogoIndex;
import com.uabc.entities.Customer;
import com.uabc.entities.Film;

import com.uabc.repository.FilmRepository;

@Service
public class FilmService implements IFilmService{

	@Autowired
	private FilmRepository filmRepository;
	
	public List<CatalogoIndex> obtenerPeliculas(){
		return filmRepository.obtenerPeliculas();
	}
	
	public List<Film> findAll(){
		//return filmRepository.obtenerPeliculas();
		return filmRepository.findAll();
	}
	public List<CatalogoIndex> filtrarPeliculasTitulo(String titulo){
		return filmRepository.filtrarPeliculasTitulo(titulo);
	}
	
	public List<CatalogoIndex> filtrarPeliculasCategoria(String categoria){

		return filmRepository.filtrarPeliculasCategoria(categoria);
	}
	
	public List<CatalogoIndex> filtrarPeliculasActor(String actor){
		return filmRepository.filtrarPeliculasActor(actor);
	}

	@Override
	public Optional<Film> findById(Integer id) {
		return filmRepository.findById(id);
	}

	@Override
	public List<String> obtenerCategorias(Integer filmId) {
		return filmRepository.obtenerCategorias(filmId);
	}

	@Override
	public List<String> obtenerActores(Integer filmId) {
		return filmRepository.obtenerActores(filmId);
	}
	
	public Film InsertFilm(Film film) 
	{
		return filmRepository.save(film);
	}

	@Override
	public Optional<Film> findById(Id id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

