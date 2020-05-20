package com.adriannavarrogabino.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adriannavarrogabino.models.entity.Autor;

public interface IAutorDao extends JpaRepository<Autor, Long> {

	@Query(value = "SELECT a FROM Autor a WHERE UPPER(a.nombre) = ?1")
	public Optional<Autor> buscarAutorPorNombre(String nombre);
}
