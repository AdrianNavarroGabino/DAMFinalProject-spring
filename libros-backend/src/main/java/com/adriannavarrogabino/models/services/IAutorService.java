package com.adriannavarrogabino.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.adriannavarrogabino.models.entity.Autor;

public interface IAutorService {

public List<Autor> findAll();
	
	public Page<Autor> findAll(Pageable pageable);
	
	public Autor findById(Long id);
	
	public Autor save(Autor autor);
	
	public void delete(Long id);
}
