package com.adriannavarrogabino.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adriannavarrogabino.models.dao.IGeneroDao;
import com.adriannavarrogabino.models.entity.Genero;

@Service
public class GeneroServiceImpl implements IGeneroService {
	
	@Autowired
	private IGeneroDao generoDao;

	@Override
	@Transactional(readOnly = true)
	public Page<Genero> findAll(Pageable pageable) {
		return generoDao.findAll(pageable);
	}
}
