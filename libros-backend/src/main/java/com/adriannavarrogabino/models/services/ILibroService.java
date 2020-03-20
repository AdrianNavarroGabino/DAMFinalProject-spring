package com.adriannavarrogabino.models.services;

import java.util.List;

import com.adriannavarrogabino.models.entity.Libro;

public interface ILibroService {
	
	public List<Libro> findAll();
	
	public Libro findById(Long id);
	
	public Libro save(Libro libro);
	
	public void delete(Long id);
}
