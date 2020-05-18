package com.adriannavarrogabino.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/generos")
	public List<Genero> showGeneros() {
		return generoService.findAll();
	}
}
