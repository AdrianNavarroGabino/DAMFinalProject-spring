package com.adriannavarrogabino.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adriannavarrogabino.models.dao.ILibroDao;
import com.adriannavarrogabino.models.entity.Libro;

@Service
public class LibroServiceImpl implements ILibroService {
	
	@Autowired
	private ILibroDao libroDao;

	@Override
	@Transactional(readOnly = true)
	public List<Libro> findAll() {
		return (List<Libro>) libroDao.findAll();
	}
}
