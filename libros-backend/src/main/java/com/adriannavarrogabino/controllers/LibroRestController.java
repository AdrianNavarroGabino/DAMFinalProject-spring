package com.adriannavarrogabino.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.adriannavarrogabino.models.entity.Libro;
import com.adriannavarrogabino.models.services.ILibroService;

// @CrossOrigin(origins = {"http://localhost:4200"})
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class LibroRestController {
	
	@Autowired
	ILibroService libroService;
	
	private final Logger log = LoggerFactory.getLogger(LibroRestController.class);
	
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
			Libro libro = libroService.findById(id);
			
			String nombreFotoAnterior = libro.getFoto();
			
			if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();

				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			
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
	
	@PostMapping("/libros/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Libro libro = libroService.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = (libro.getIsbn10() != null ? libro.getIsbn10() : libro.getIsbn13()) + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			log.info(rutaArchivo.toString());

			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del libro " + nombreArchivo);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = libro.getFoto();

			if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();

				if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}

			libro.setFoto(nombreArchivo);

			libroService.save(libro);

			response.put("libro", libro);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		log.info(rutaArchivo.toString());
		Resource recurso = null;

		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() || !recurso.isReadable())
		{
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-portada.png").toAbsolutePath();
			
			try {
				recurso = new UrlResource(rutaArchivo.toUri());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			log.error("Error. No se pudo cargar la imagen");
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
}
