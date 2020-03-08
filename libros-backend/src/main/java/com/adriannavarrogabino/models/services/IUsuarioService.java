package com.adriannavarrogabino.models.services;

import java.util.List;

import com.adriannavarrogabino.models.entity.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();
}
