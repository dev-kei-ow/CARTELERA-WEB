package com.mycomp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mycomp.model.Genero;
import com.mycomp.repository.GeneroRepository;

@Service
@Transactional
public class GeneroServiceImpl implements GeneroService {

	@Autowired
	private GeneroRepository genrepo;

	@Override
	public List<Genero> findAll(Sort sor) {

		return genrepo.findAll();
	}

	@Override
	public Genero getFindById(Integer id) {

		return genrepo.findById(id).get();
	}

	@Override
	public Genero save(Genero gen) {
		
		return genrepo.save(gen);
	}

	@Override
	public void delete(Integer id) {
		
		genrepo.deleteById(id);

	}

	@Override
	public void update(Integer id, Genero gen) {
		// TODO Auto-generated method stub

	}

}
