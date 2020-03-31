package com.adriannavarrogabino.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriannavarrogabino.models.entity.Libro;

public interface ILibroDao extends JpaRepository<Libro, Long> {

}
