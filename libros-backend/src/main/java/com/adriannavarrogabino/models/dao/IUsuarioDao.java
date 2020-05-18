package com.adriannavarrogabino.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adriannavarrogabino.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
	
	public Usuario findByUsername(String username);
	
	@Query(value = "SELECT * FROM usuarios WHERE id IN (SELECT seguido FROM usuarios_seguir WHERE seguidor = ?1 AND seguido = ?2) LIMIT 1", nativeQuery = true)
	public Optional<Usuario> findSeguido(Long idSeguidor, Long idSeguido);
}
