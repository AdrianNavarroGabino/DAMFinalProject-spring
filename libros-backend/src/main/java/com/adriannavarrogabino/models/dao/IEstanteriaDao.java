package com.adriannavarrogabino.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adriannavarrogabino.models.entity.Estanteria;

public interface IEstanteriaDao extends JpaRepository<Estanteria, Long> {

	@Query(value = "SELECT * FROM estanterias WHERE usuario_id = ?1 AND nombre = ?2 LIMIT 1", nativeQuery = true)
	public Optional<Estanteria> findPorUsuarioYNombre(Long idUsuario, String nombreEstanteria);
	
	@Query(value = "SELECT * FROM estanterias WHERE usuario_id = ?1", nativeQuery = true)
	public List<Estanteria> findEstanteriasPorUsuario(Long idUsuario);
}
