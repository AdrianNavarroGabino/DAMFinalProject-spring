package com.adriannavarrogabino.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adriannavarrogabino.models.entity.Libro;

public interface ILibroDao extends JpaRepository<Libro, Long> {

	@Query(value = "SELECT * FROM libros WHERE id_autor = ?1",
		nativeQuery = true)
	public Page<Libro> findLibrosPorAutor(Long id, Pageable pageable);
	
	@Query(value =
		"SELECT * FROM libros WHERE id IN (SELECT id_libro FROM " + 
		"libros_generos WHERE id_genero = ?1)",
		nativeQuery = true)
	public Page<Libro> findLibrosPorGenero(Long id, Pageable pageable);
	
	@Query(value = "SELECT * FROM libros ORDER BY RANDOM() LIMIT 12",
		nativeQuery = true)
	public List<Libro> findRandomLibros();
	
	@Query(value =
		"SELECT * FROM libros WHERE UPPER(titulo) LIKE '%'||?1||'%' OR " + 
		"id_autor IN (SELECT id FROM autores WHERE UPPER(nombre) LIKE " + 
		"'%'||?1||'%')", nativeQuery = true)
	public Page<Libro> buscarLibros(String buscar, Pageable pageable);
	
	@Query(value =
		"SELECT * FROM libros WHERE id IN (SELECT libro_id FROM " +
		"estanterias_libros WHERE estanteria_id = ?1)", nativeQuery = true)
	public Page<Libro> findEstanteria(Long idEstanteria, Pageable pageable);
}
