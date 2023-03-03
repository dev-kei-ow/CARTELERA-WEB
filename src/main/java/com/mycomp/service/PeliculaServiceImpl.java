package com.mycomp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mycomp.model.Pelicula;
import com.mycomp.repository.PeliculaRepository;

@Service
@Transactional
public class PeliculaServiceImpl implements PeliculaService {

	@Autowired
	private PeliculaRepository pelirepo;

	@Override
	public List<Pelicula> findAll() {

		return pelirepo.findAll();
	}
	
	@Override
	public Page<Pelicula> getAllPagination(Pageable page) {
		
		return pelirepo.findAll(page);
	}

	@Override
	public Pelicula getFindById(Integer id) {

		return pelirepo.findById(id).get();
	}

	@Override
	public Pelicula save(Pelicula peli) {

		return pelirepo.save(peli);
	}

	@Override
	public void delete(Integer id) {

		pelirepo.deleteById(id);

	}

	@Override
	public void update(Integer id, Pelicula peli) {
		// TODO Auto-generated method stub

	}



}
