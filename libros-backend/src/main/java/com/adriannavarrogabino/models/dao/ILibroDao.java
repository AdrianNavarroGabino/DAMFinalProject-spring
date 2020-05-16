package com.adriannavarrogabino.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adriannavarrogabino.models.entity.Libro;

public interface ILibroDao extends JpaRepository<Libro, Long> {

	@Query(value = "SELECT l FROM Libro l WHERE l.autor = ?2")
	public Page<Libro> findLibrosPorAutor(Pageable pageable, Long id);
	
	@Query(value = "SELECT * FROM libros ORDER BY RANDOM() LIMIT 12", nativeQuery = true)
	public List<Libro> findRandomLibros();
	
	@Query(value = "SELECT l FROM Libro l WHERE UPPER(l.titulo) LIKE '%'||UPPER(?2)||'%' OR UPPER(l.autor) LIKE '%'||UPPER(?2)||'%'")
	public Page<Libro> buscarLibros(Pageable pageable, String buscar);
}
