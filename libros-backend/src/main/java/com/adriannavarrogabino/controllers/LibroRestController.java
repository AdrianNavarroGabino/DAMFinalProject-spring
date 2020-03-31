package com.adriannavarrogabino.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public Page<Libro> index()
	{
		Pageable pageable = PageRequest.of(0, 10);
		return libroService.findAll(pageable);
	}
	
	@GetMapping("/libros/page/{page}")
	public Page<Libro> index(@PathVariable Integer page)
	{
		Pageable pageable = PageRequest.of(page, 10);
		return libroService.findAll(pageable);
	}
	
	@GetMapping("/libros/{id}")
	public ResponseEntity<?> show(@PathVariable Long id)
	{
		Libro libro = null;
		Map<String, Object> response = new HashMap<>();
		
		try
		{
			libro = libroService.findById(id);
		}
		catch(DataAccessException e)
		{
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage()
					.concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(libro == null)
		{
			response.put("mensaje", "El libro que buscas no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Libro>(libro, HttpStatus.OK);
	}
	
	@PostMapping("/libros")
	public ResponseEntity<?> create(@RequestBody Libro libro)
	{
		Libro libroNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try
		{
			libroNew = libroService.save(libro);
		}
		catch(DataAccessException e)
		{
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage()
					.concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El libro ha sido creado con éxito");
		response.put("libro", libroNew);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/libros/{id}")
	public ResponseEntity<?> update(@RequestBody Libro libro, @PathVariable Long id)
	{
		Libro libroActual = libroService.findById(id);
		Libro libroUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(libroActual == null)
		{
			response.put("mensaje", "El libro no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try
		{
			libroActual.setTitulo(libro.getTitulo());
			libroActual.setEditorial(libro.getEditorial());
			libroActual.setFechaPublicacion(libro.getFechaPublicacion());
			
			libroUpdated = libroService.save(libroActual);
		}
		catch(DataAccessException e)
		{
			response.put("mensaje", "Error al realizar el update en la base de datos");
			response.put("error", e.getMessage()
					.concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El libro ha sido actualizado con éxito");
		response.put("libro", libroUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/libros/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		Map<String, Object> response = new HashMap<>();
		try
		{
			libroService.delete(id);
		}
		catch(DataAccessException e)
		{
			response.put("mensaje", "Error al realizar el delete en la base de datos");
			response.put("error", e.getMessage()
					.concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Libro eliminado con éxito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
