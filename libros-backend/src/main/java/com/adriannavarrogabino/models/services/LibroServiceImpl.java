package com.adriannavarrogabino.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Override
	@Transactional(readOnly = true)
	public Page<Libro> findAll(Pageable pageable) {
		return libroDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Libro findById(Long id) {
		return libroDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Libro save(Libro libro) {
		return libroDao.save(libro);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		libroDao.deleteById(id);
	}

	@Override
	public Page<Libro> findLibrosPorAutor(Long autor, Pageable pageable) {
		return libroDao.findLibrosPorAutor(autor, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Libro> findRandomLibros() {
		return libroDao.findRandomLibros();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Libro> buscarLibros(String buscar, Pageable pageable) {
		return libroDao.buscarLibros(buscar.toUpperCase(), pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Libro> findLibrosPorGenero(Long id, Pageable pageable) {
		return libroDao.findLibrosPorGenero(id, pageable);
	}
}
