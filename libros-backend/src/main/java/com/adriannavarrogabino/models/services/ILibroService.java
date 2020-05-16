package com.adriannavarrogabino.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.adriannavarrogabino.models.entity.Libro;

public interface ILibroService {
	
	public List<Libro> findAll();
	
	public Page<Libro> findAll(Pageable pageable);
	
	public List<Libro> findRandomLibros();
	
	public Page<Libro> findLibrosPorAutor(Long autor, Pageable pageable);
	
	public Page<Libro> buscarLibros(Pageable pageable, String buscar);
	
	public Libro findById(Long id);
	
	public Libro save(Libro libro);
	
	public void delete(Long id);
}
