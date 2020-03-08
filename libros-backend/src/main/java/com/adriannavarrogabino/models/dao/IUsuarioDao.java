package com.adriannavarrogabino.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.adriannavarrogabino.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

}
