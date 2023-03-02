package com.mycomp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.mycomp.model.*;

import com.mycomp.service.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private PeliculaService pelisrvc;

	@Autowired
	private GeneroService gensrvc;

	@Autowired
	private StorageService strgsrvc;

	@GetMapping("")
	ModelAndView index() {

		List<Pelicula> peli = pelisrvc.findAll();

		return new ModelAndView("ModuloAdmin/index")
				.addObject("objPeliculas", peli);

	}

	@GetMapping("/peliculas/create")
	ModelAndView nuevaPelicula() {

		/* Sort.by -> ORDENA LOS GENEROS POR EL NOMBRE */
		List<Genero> lstGeneros = gensrvc.findAll(Sort.by("nombre"));

		return new ModelAndView("ModuloAdmin/nueva-pelicula")
				.addObject("objPeliculas", new Pelicula())
				.addObject("objGeneros", lstGeneros);

	}

	@PostMapping("/peliculas/save")
	ModelAndView savePelicula(Pelicula peli) {

		if (peli.getIdPelicula() == null) { // CUANDO NO CARGAS EL ID, OSEA ESTAS GUARDANDO

			String ruta = strgsrvc.storage(peli.getPortada());

			peli.setRutaPortada(ruta);

		} else {// CUANDO CARGAS EL ID, ESTAS EDITANDO

			if (!peli.getPortada().isEmpty()) { // CUANDO SE EDITA TBN LA IMAGEN

				strgsrvc.deleteFile(peli.getRutaPortada());

				String ruta = strgsrvc.storage(peli.getPortada());

				peli.setRutaPortada(ruta);

			} else { // CUANDO EDITAMOS EL PRODUCTO PERO NO CAMBIAMOS LA IMAGEN

				Pelicula p = new Pelicula();

				p = pelisrvc.getFindById(peli.getIdPelicula());

				peli.setRutaPortada(p.getRutaPortada());
			}
		}

		pelisrvc.save(peli);

		return new ModelAndView("redirect:/admin");

	}

	@GetMapping("/peliculas/edit/{id}")
	ModelAndView updatePelicula(@PathVariable("id") Integer id) {

		Pelicula peli = pelisrvc.getFindById(id);
		List<Genero> lstGeneros = gensrvc.findAll(Sort.by("nombre"));

		return new ModelAndView("ModuloAdmin/nueva-pelicula").addObject("objPeliculas", peli).addObject("objGeneros",
				lstGeneros);

	}

	@PostMapping("/peliculas/delete/{id}")
	String deletePelicula(@PathVariable Integer id) {

		Pelicula peli = new Pelicula();

		peli = pelisrvc.getFindById(id);

		strgsrvc.deleteFile(peli.getRutaPortada());

		pelisrvc.delete(id);

		return "redirect:/admin";

	}

}
