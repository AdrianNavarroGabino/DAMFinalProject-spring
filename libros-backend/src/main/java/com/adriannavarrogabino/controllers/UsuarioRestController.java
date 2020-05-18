package com.adriannavarrogabino.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adriannavarrogabino.models.entity.Estanteria;
import com.adriannavarrogabino.models.entity.Libro;
import com.adriannavarrogabino.models.entity.Usuario;
import com.adriannavarrogabino.models.services.IEstanteriaService;
import com.adriannavarrogabino.models.services.IUsuarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	
	@Autowired IUsuarioService usuarioService;
	
	@Autowired IEstanteriaService estanteriaService;
	
	@GetMapping("/usuarios")
	public List<Usuario> index()
	{
		return usuarioService.findAll();
	}
	
	@GetMapping("/usuario/page/{page}")
	public Page<Usuario> indexPaginado(@PathVariable Integer page)
	{
		Pageable pageable = PageRequest.of(page, 10);
		return usuarioService.findAll(pageable);
	}
	
	@GetMapping("/usuario/{id}")
	public Usuario verUsuario(@PathVariable Long id)
	{
		return usuarioService.findById(id);
	}
	
	@GetMapping("/usuario/{idSeguidor}/{idSeguido}")
	public Usuario verSeguido(@PathVariable Long idSeguidor, @PathVariable Long idSeguido)
	{
		return usuarioService.findSeguido(idSeguidor, idSeguido);
	}
	
	@GetMapping("/usuario/{idUsuario}/estanterias/{nombreEstanteria}")
	public Estanteria getEstanteria(@PathVariable Long idUsuario, @PathVariable String nombreEstanteria) {
		return estanteriaService.findPorUsuarioYNombre(idUsuario, nombreEstanteria);
	}
	
	@PostMapping("/usuario/estanterias")
	public Estanteria guardarEstanteria(@RequestBody Estanteria estanteria) {
		return estanteriaService.save(estanteria);
	}
	
	@PutMapping("/usuario/{idUsuario}/estanterias")
	public Usuario addEstanteria(@PathVariable Long idUsuario)
	{
		Usuario u = usuarioService.findById(idUsuario);
		
		List<Estanteria> estanterias = estanteriaService.findEstanteriasPorUsuario(idUsuario);
		
		u.setEstanterias(estanterias);
		
		return usuarioService.save(u);
	}
	
	@PutMapping("/usuario/estanterias")
	public Estanteria addLibroEstanteria(@PathVariable Long idEstanteria, @RequestBody Libro libro) {
		Estanteria estanteria = estanteriaService.findById(idEstanteria);
		
		estanteria.addLibro(libro);
		
		return estanteriaService.save(estanteria);
	}
	
	@PutMapping("/usuario/{idSeguidor}")
	public Usuario addSeguido(@PathVariable Long idSeguidor, @RequestBody Usuario usuarioSeguido) {
		Usuario seguidor = usuarioService.findById(idSeguidor);
		
		List<Usuario> seguidos = seguidor.getSeguidos();
		
		seguidos.add(usuarioSeguido);
		
		seguidor.setSeguidos(seguidos);
		
		return usuarioService.save(seguidor);
	}
	
	@PutMapping("/usuario/unfollow/{idSeguidor}")
	public Usuario dejarDeSeguir(@PathVariable Long idSeguidor, @RequestBody Usuario usuarioSeguido) {
		Usuario seguidor = usuarioService.findById(idSeguidor);
		Usuario seguido = usuarioService.findById(usuarioSeguido.getId());
		
		List<Usuario> seguidos = seguidor.getSeguidos();
		
		seguidos.remove(seguido);
		
		seguidos.forEach(a -> System.out.println(a.getNombre()));
		
		seguidor.setSeguidos(seguidos);
		
		return usuarioService.save(seguidor);
	}
}
