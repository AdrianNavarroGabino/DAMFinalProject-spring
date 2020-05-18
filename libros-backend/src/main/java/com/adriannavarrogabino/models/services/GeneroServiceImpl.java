package com.adriannavarrogabino.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Genero> findAll() {
		return (List<Genero>) generoDao.findAll();
	}

}
