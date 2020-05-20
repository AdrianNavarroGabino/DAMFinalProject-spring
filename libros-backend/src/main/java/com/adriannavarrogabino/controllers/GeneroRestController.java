package com.adriannavarrogabino.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adriannavarrogabino.models.entity.Genero;
import com.adriannavarrogabino.models.services.IGeneroService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class GeneroRestController {

	@Autowired
	private IGeneroService generoService;
	
	@GetMapping("/generos/page/{page}")
	public Page<Genero> showGenerosPage(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 10);
		return generoService.findAll(pageable);
	}
	
	@GetMapping("/generos")
	public List<Genero> showGeneros() {
		return generoService.findAll();
	}
	
	@GetMapping("/generos/{id}")
	public Genero getById(@PathVariable Long id) {
		return generoService.findById(id);
	}
}
