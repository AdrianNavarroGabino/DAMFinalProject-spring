package com.adriannavarrogabino.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adriannavarrogabino.models.entity.Usuario;
import com.adriannavarrogabino.models.services.IUsuarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	
	@Autowired IUsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<Usuario> index()
	{
		return usuarioService.findAll();
	}
	
	@GetMapping("/usuario/{id}")
	public Usuario verUsuario(@PathVariable Long id)
	{
		return usuarioService.findById(id);
	}
}
