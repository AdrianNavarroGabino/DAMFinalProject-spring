package com.adriannavarrogabino.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adriannavarrogabino.models.entity.Libro;
import com.adriannavarrogabino.models.services.ILibroService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class LibroRestController {
	
	@Autowired
	ILibroService libroService;
	
	@GetMapping("/libros")
	public List<Libro> index()
	{
		return libroService.findAll();
	}
	
	@GetMapping("/libros/{id}")
	public Libro show(@PathVariable Long id)
	{
		return libroService.findById(id);
	}
	
	@PostMapping("/libros")
	@ResponseStatus(HttpStatus.CREATED)
	public Libro create(@RequestBody Libro libro)
	{
		return libroService.save(libro);
	}
	
	@PutMapping("/libros/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Libro update(@RequestBody Libro libro, @PathVariable Long id)
	{
		Libro libroActual = libroService.findById(id);
		
		libroActual.setTitulo(libro.getTitulo());
		libroActual.setEditorial(libro.getEditorial());
		libroActual.setFechaPublicacion(libro.getFechaPublicacion());
		
		return libroService.save(libroActual);
	}
	
	@DeleteMapping("/libros/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id)
	{
		libroService.delete(id);
	}
}
