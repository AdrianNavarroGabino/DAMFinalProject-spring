package com.adriannavarrogabino.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.adriannavarrogabino.models.entity.Genero;

public interface IGeneroService {
	public Page<Genero> findAll(Pageable pageable);
	
	public List<Genero> findAll();
	
	public Genero findById(Long id);
}
