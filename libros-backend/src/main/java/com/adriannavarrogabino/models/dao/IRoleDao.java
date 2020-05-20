package com.adriannavarrogabino.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.adriannavarrogabino.models.entity.Role;

public interface IRoleDao extends CrudRepository<Role, Long> {
	
	@Query(value = "SELECT * FROM roles WHERE nombre = ?1 LIMIT 1",
		nativeQuery = true)
	public Optional<Role> buscarRolePorNombre(String nombre);

}
