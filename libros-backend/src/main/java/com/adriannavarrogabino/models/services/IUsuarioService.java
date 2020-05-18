package com.adriannavarrogabino.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.adriannavarrogabino.models.entity.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();
	
	public Page<Usuario> findAll(Pageable pageable);
	
	public Usuario findByUsername(String username);
	
	public Usuario findById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public Usuario findSeguido(Long idSeguidor, Long idSeguido);
}
