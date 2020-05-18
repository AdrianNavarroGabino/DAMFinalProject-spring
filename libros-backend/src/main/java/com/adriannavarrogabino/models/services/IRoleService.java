package com.adriannavarrogabino.models.services;

import java.util.List;

import com.adriannavarrogabino.models.entity.Role;

public interface IRoleService {
	public List<Role> findAll();
	
	public Role findById(Long id);
	
	public Role buscarRolePorNombre(String nombre);
	
	public Role save(Role role);
	
	public void delete(Long id);
}
