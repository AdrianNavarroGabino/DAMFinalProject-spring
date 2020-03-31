package com.adriannavarrogabino.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.adriannavarrogabino.models.entity.Libro;

public interface ILibroService {
	
	public List<Libro> findAll();
	
	public Page<Libro> findAll(Pageable pageable);
	
	public Libro findById(Long id);
	
	public Libro save(Libro libro);
	
	public void delete(Long id);
}
