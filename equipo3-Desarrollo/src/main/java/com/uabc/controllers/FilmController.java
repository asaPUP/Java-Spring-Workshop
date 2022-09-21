package com.uabc.controllers;

import java.io.PrintWriter;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uabc.entities.Actor;
import com.uabc.entities.CatalogoIndex;
import com.uabc.entities.Category;
import com.uabc.entities.Country;
import com.uabc.entities.Customer;
import com.uabc.entities.Film;
import com.uabc.entities.FilmActor;
import com.uabc.entities.FilmActorId;
import com.uabc.entities.FilmCategory;
import com.uabc.entities.FilmCategoryId;
import com.uabc.entities.Language;
import com.uabc.services.ActorService;
import com.uabc.services.CategoryService;
import com.uabc.services.FilmActorService;
import com.uabc.services.FilmCategoryService;
import com.uabc.services.FilmService;
import com.uabc.services.InventoryService;
import com.uabc.services.LanguageService;

@Controller
public class FilmController {

	@Autowired
	private FilmService filmService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private FilmCategoryService categoryService2;
	
	@Autowired
	private FilmActorService filmActorService;

	@RequestMapping(value = { "/films" })
	public String index(Model model, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		
		List<Language> languages = new ArrayList<Language>();
		languages = languageService.findAll();
		
		List<Category> categories = new ArrayList<Category>();
		categories = categoryService.findAll();
		
		List<Actor> actors = new ArrayList<Actor>();
		actors = actorService.findAll();
		
		model.addAttribute("Film", new Film());
		model.addAttribute("Language", languages);
		model.addAttribute("Category", categories);
		model.addAttribute("Actor", actors);

		return "views/registro_peliculas";
	}

	@RequestMapping(value = { "/InsertFilm" }, method = RequestMethod.POST)
	public String insert(Film film, Model model, HttpServletRequest request, HttpServletResponse response,
			Principal principal, RedirectAttributes redirectAttrs) {
	
		
		FilmActor filmActor = new FilmActor();
		FilmActorId actorId = new FilmActorId();
		
		
		FilmCategory filmCategory = new FilmCategory();
		FilmCategoryId filmCategoryId = new FilmCategoryId();
		
		
		Map<String, String[]> datos = request.getParameterMap();
		String[] titulo = datos.get("title");
		String[] desc = datos.get("description");
		String[] durac = datos.get("length");
		String[] copias = datos.get("addAmount");
		
		String[] rentalr = datos.get("rentalRate");
		String[] replacementc = datos.get("replacementCost");
		
		String[] categorias = datos.get("selectCategory");
		String[] lenguajes = datos.get("selectLanguage");
		
		String[] nombres = datos.get("nombre[]");
		String[] apellidos = datos.get("apellido[]");
		
		
		
		
		
		film.setTitle(titulo[0]);
		film.setLanguage(languageService.findById(Integer.parseInt(lenguajes[0])).get());
		film.setDescription(desc[0]);
		film.setLength(Short.parseShort(copias[0]));
		film.setRentalRate(Float.parseFloat(rentalr[0]));
		Date tempdate = new Date(System.currentTimeMillis());
		java.sql.Timestamp Last_date = new Timestamp(tempdate.getTime());
		film.setLast_update(Last_date);
		film.setReplacementCost(Integer.parseInt(replacementc[0]));
		film.setRentalDuration(Short.parseShort(durac[0])); //LLENAR
		
		film=filmService.InsertFilm(film);
		
		for(int i =0; i<categorias.length;i++) {
			
			filmCategoryId.setFilmId(film.getFilmId());
			filmCategoryId.setCategoryId(Integer.parseInt(categorias[i]));
			filmCategory.setFilmCategoryId(filmCategoryId);
			filmCategory.setLastUpdate(Last_date);
			
			categoryService2.insertFilmCategory(filmCategory);
		}
		
		for(int i =0; i<nombres.length;i++) {
			
			Actor actor = new Actor();
			
			actor.setFirstName(nombres[i]);
			actor.setLastName(apellidos[i]);
			actor.setLastUpdate(Last_date);
			actorService.insertActor(actor);
			
			actorId.setActorId(actor.getActorId());
			actorId.setFilmId(film.getFilmId());
			
			filmActor.setFilmActorId(actorId);
			filmActor.setLastUpdate(Last_date);
			
			filmActorService.insertFilmActor(filmActor);
		}
		
		
		redirectAttrs
        .addFlashAttribute("mensaje", "Se Registro correctamente la pelicula")
        .addFlashAttribute("clase", "Success");
		
		return "redirect:/films";
	}

}
