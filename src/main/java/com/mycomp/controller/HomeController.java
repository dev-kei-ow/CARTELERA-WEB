package com.mycomp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycomp.model.Pelicula;
import com.mycomp.service.PeliculaService;

@Controller
@RequestMapping("")
public class HomeController {

	@Autowired
	private PeliculaService pelisrvc;

	@GetMapping("/")
	public ModelAndView index() {

		List<Pelicula> ultiPelis = 
				pelisrvc.getAllPagination(PageRequest.of(0, 4, Sort.by("titulo")
			    .descending()))
				.toList();
		return new ModelAndView("index")
				   .addObject("lstUltiPelis", ultiPelis);

	}
	
	@GetMapping("/peliculas")
	public ModelAndView listarPeliculas(@PageableDefault(sort = "titulo", direction = Sort.Direction.DESC) Pageable page) {
		
		Page<Pelicula> pelis = pelisrvc.getAllPagination(page);

		return new ModelAndView("peliculas")
				   .addObject("lstPelis", pelis);

	}
	
	@GetMapping("/peliculas/{id}")
	public ModelAndView detallePeliculas(@PathVariable Integer id) {
		
		Pelicula peli = pelisrvc.getFindById(id);

		return new ModelAndView("detalle-peliculas")
				.addObject("detPelicula", peli);

	}
	
	


}
