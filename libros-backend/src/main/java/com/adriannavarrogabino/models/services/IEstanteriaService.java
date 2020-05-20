package com.adriannavarrogabino.models.services;

import java.util.List;

import com.adriannavarrogabino.models.entity.Estanteria;

public interface IEstanteriaService {

	public Estanteria save(Estanteria estanteria);
	
	public Estanteria findById(Long id);
	
	public Estanteria findPorUsuarioYNombre(
			Long idUsuario, String nombreEstanteria);
	
	public List<Estanteria> findEstanteriasPorUsuario(Long idUsuario);
}
