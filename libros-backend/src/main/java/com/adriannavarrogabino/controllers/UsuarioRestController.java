package com.adriannavarrogabino.controllers;

import java.util.Date;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.adriannavarrogabino.models.entity.Notificacion;
import com.adriannavarrogabino.models.entity.Role;
import com.adriannavarrogabino.models.entity.Usuario;
import com.adriannavarrogabino.models.services.IEstanteriaService;
import com.adriannavarrogabino.models.services.ILibroService;
import com.adriannavarrogabino.models.services.IRoleService;
import com.adriannavarrogabino.models.services.IUsuarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	
	@Autowired IUsuarioService usuarioService;
	
	@Autowired IEstanteriaService estanteriaService;
	
	@Autowired IRoleService roleService;
	
	@Autowired ILibroService libroService;
	
	@Autowired BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/usuarios")
	public List<Usuario> index() {
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
	public Usuario verSeguido(@PathVariable Long idSeguidor,
			@PathVariable Long idSeguido)
	{
		return usuarioService.findSeguido(idSeguidor, idSeguido);
	}
	
	@PostMapping("/usuario")
	public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario)
	{
		Usuario usuarioNew = null;
		List<Role> roles = usuario.getRoles();
		Role role = roleService.buscarRolePorNombre("ROLE_USER");
		roles.add(role);
		usuario.setRoles(roles);
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		
		Map<String, Object> response = new HashMap<>();
		
		try
		{
			usuarioNew = usuarioService.save(usuario);
		}
		catch(DataAccessException e)
		{
			response.put("mensaje",
					"Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ")
					.concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El usuario ha sido creado con éxito");
		response.put("usuario", usuarioNew);
		
		return new ResponseEntity<Map<String, Object>>(response,
				HttpStatus.CREATED);
	}
	
	@GetMapping("/usuario/{idUsuario}/estanterias/{nombreEstanteria}")
	public Estanteria getEstanteria(@PathVariable Long idUsuario,
			@PathVariable String nombreEstanteria) {
		return estanteriaService.findPorUsuarioYNombre(
				idUsuario, nombreEstanteria);
	}
	
	@PostMapping("/usuario/estanterias")
	public Estanteria guardarEstanteria(@RequestBody Estanteria estanteria) {
		return estanteriaService.save(estanteria);
	}
	
	@PutMapping("/usuario/{idUsuario}/estanterias")
	public Usuario addEstanteria(@PathVariable Long idUsuario,
			@RequestBody Estanteria estanteria) {
		Usuario u = usuarioService.findById(idUsuario);
		
		List<Estanteria> estanterias =
				estanteriaService.findEstanteriasPorUsuario(idUsuario);
		
		estanterias.add(estanteria);
		
		u.setEstanterias(estanterias);
		
		return usuarioService.save(u);
	}
	
	@PutMapping("/usuario/estanterias/{idEstanteria}")
	public Estanteria addLibroEstanteria(@PathVariable Long idEstanteria,
			@RequestBody Libro libro) {
		Estanteria estanteria = estanteriaService.findById(idEstanteria);
		Libro l = libroService.findById(libro.getId());
		
		estanteria.addLibro(l);
		
		return estanteriaService.save(estanteria);
	}
	
	@PutMapping("/usuario/{idSeguidor}")
	public Usuario addSeguido(@PathVariable Long idSeguidor,
			@RequestBody Usuario usuarioSeguido) {
		Usuario seguidor = usuarioService.findById(idSeguidor);
		Usuario seguido = usuarioService.findById(usuarioSeguido.getId());
		
		List<Usuario> seguidos = seguidor.getSeguidos();
		
		seguidos.add(seguido);
		
		seguidor.setSeguidos(seguidos);
		
		return usuarioService.save(seguidor);
	}
	
	@PutMapping("/usuario/unfollow/{idSeguidor}")
	public Usuario dejarDeSeguir(@PathVariable Long idSeguidor,
			@RequestBody Usuario usuarioSeguido) {
		Usuario seguidor = usuarioService.findById(idSeguidor);
		Usuario seguido = usuarioService.findById(usuarioSeguido.getId());
		
		List<Usuario> seguidos = seguidor.getSeguidos();
		
		seguidos.remove(seguido);
		
		seguidos.forEach(a -> System.out.println(a.getNombre()));
		
		seguidor.setSeguidos(seguidos);
		
		return usuarioService.save(seguidor);
	}
	
	@PutMapping("/usuario/ultimoacceso/{username}")
	public Usuario actualizarUltimoAcceso(@PathVariable String username) {
		Usuario usuario = usuarioService.findByUsername(username);
		
		usuario.setUltimoAcceso(usuario.getAccesoActual());
		usuario.setAccesoActual(new Date());
		
		return usuarioService.save(usuario);
	}
	
	@PutMapping("/usuario/notificaciones/{id}")
	public Usuario marcarNotificacionesLeidas(@PathVariable Long id) {
		Usuario u = usuarioService.findById(id);
		
		List<Notificacion> notificaciones = u.getNotificaciones();
		
		for(Notificacion n: notificaciones) {
			n.setLeido(true);
		}
		
		u.setNotificaciones(notificaciones);
		
		return usuarioService.save(u);
	}
	
	@PutMapping("/usuario/notificaciones/nueva/{id}")
	public Usuario addNotificacion(@PathVariable Long id,
			@RequestBody String notificacion) {
		Usuario u = usuarioService.findById(id);
		
		List<Notificacion> notificaciones = u.getNotificaciones();
		
		Notificacion n = new Notificacion();
		
		n.setFecha(new Date());
		n.setLeido(false);
		n.setNotificacion(notificacion);
		
		notificaciones.add(n);
		
		u.setNotificaciones(notificaciones);
		
		return usuarioService.save(u);
	}
}
