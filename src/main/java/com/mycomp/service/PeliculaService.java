package com.mycomp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mycomp.model.Pelicula;

public interface PeliculaService {

	public List<Pelicula> findAll();
	
	public Page<Pelicula> getAllPagination(Pageable page);

	public Pelicula getFindById(Integer id);

	public Pelicula save(Pelicula peli);

	public void delete(Integer id);

	public void update(Integer id, Pelicula peli);

}
