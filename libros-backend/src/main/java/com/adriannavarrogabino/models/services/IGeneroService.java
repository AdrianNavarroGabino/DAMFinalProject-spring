package com.adriannavarrogabino.models.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.adriannavarrogabino.models.entity.Genero;

public interface IGeneroService {
	public Page<Genero> findAll(Pageable pageable);
}
