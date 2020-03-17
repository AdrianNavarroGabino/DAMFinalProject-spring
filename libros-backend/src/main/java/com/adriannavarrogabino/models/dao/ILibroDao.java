package com.adriannavarrogabino.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.adriannavarrogabino.models.entity.Libro;

public interface ILibroDao extends CrudRepository<Libro, Long> {

}
