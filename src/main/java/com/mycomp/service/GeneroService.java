package com.mycomp.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.mycomp.model.Genero;

public interface GeneroService {

	public List<Genero> findAll(Sort sor);

	public Genero getFindById(Integer id);

	public Genero save(Genero gen);

	public void delete(Integer id);

	public void update(Integer id, Genero gen);

}
