package com.adriannavarrogabino.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adriannavarrogabino.models.entity.Libro;

public interface ILibroDao extends JpaRepository<Libro, Long> {

	@Query(value = "SELECT l FROM Libro l WHERE l.autor = ?2")
	public Page<Libro> findLibrosPorAutor(Pageable pageable, String autor);
}
