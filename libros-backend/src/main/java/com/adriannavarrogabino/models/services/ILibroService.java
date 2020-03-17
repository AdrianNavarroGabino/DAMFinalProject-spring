package com.adriannavarrogabino.models.services;

import java.util.List;

import com.adriannavarrogabino.models.entity.Libro;

public interface ILibroService {
	
	public List<Libro> findAll();
}
