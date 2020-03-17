package com.adriannavarrogabino.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriannavarrogabino.models.dao.IAutorDao;
import com.adriannavarrogabino.models.entity.Autor;

@Service
public class AutorServiceImpl implements IAutorService {
	
	@Autowired
	IAutorDao autorDao;

	@Override
	public List<Autor> findAll() {
		
		return (List<Autor>) autorDao.findAll();
	}

}
