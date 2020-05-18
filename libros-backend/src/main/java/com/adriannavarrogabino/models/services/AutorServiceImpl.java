package com.adriannavarrogabino.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adriannavarrogabino.models.dao.IAutorDao;
import com.adriannavarrogabino.models.entity.Autor;

@Service
public class AutorServiceImpl implements IAutorService {
	
	@Autowired
	private IAutorDao autorDao;

	@Override
	@Transactional(readOnly = true)
	public List<Autor> findAll() {
		return autorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Autor> findAll(Pageable pageable) {
		return autorDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Autor findById(Long id) {
		return autorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Autor save(Autor autor) {
		return autorDao.save(autor);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		autorDao.deleteById(id);
	}

}
