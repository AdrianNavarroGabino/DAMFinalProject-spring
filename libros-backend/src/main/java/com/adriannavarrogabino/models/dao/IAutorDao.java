package com.adriannavarrogabino.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.adriannavarrogabino.models.entity.Autor;

public interface IAutorDao extends CrudRepository<Autor, Long> {

}
