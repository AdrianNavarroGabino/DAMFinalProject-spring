package com.adriannavarrogabino.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriannavarrogabino.models.entity.Autor;

public interface IAutorDao extends JpaRepository<Autor, Long> {

}
