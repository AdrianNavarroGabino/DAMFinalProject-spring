package com.adriannavarrogabino.models.services;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriannavarrogabino.models.dao.IEstanteriaDao;
import com.adriannavarrogabino.models.entity.Estanteria;

@Service
public class EstanteriaServiceImpl implements IEstanteriaService {
	
	@Autowired
	private IEstanteriaDao estanteriaDao;

	@Override
	@Transactional
	public Estanteria save(Estanteria estanteria) {
		
		return estanteriaDao.save(estanteria);
	}

	@Override
	@Transactional(readOnly = true)
	public Estanteria findById(Long id) {
		return estanteriaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Estanteria findPorUsuarioYNombre(Long idUsuario, String nombreEstanteria) {
		return estanteriaDao.findPorUsuarioYNombre(idUsuario, nombreEstanteria).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Estanteria> findEstanteriasPorUsuario(Long idUsuario) {
		return estanteriaDao.findEstanteriasPorUsuario(idUsuario);
	}

}
