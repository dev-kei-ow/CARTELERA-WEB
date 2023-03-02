package com.mycomp.service;

import java.util.List;



import com.mycomp.model.Pelicula;

public interface PeliculaService {

	public List<Pelicula> findAll();

	public Pelicula getFindById(Integer id);

	public Pelicula save(Pelicula peli);

	public void delete(Integer id);

	public void update(Integer id, Pelicula peli);

}
