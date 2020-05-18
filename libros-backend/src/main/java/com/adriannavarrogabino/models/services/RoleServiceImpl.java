package com.adriannavarrogabino.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adriannavarrogabino.models.dao.IRoleDao;
import com.adriannavarrogabino.models.entity.Role;

@Service
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private IRoleDao roleDao;

	@Override
	@Transactional(readOnly = true)
	public List<Role> findAll() {
		return (List<Role>) roleDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Role findById(Long id) {
		return roleDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Role buscarRolePorNombre(String nombre) {
		return roleDao.buscarRolePorNombre(nombre).orElse(null);
	}

	@Override
	@Transactional
	public Role save(Role role) {
		return roleDao.save(role);
	}

	@Override
	@Transactional
	public void delete(Long id) {

		roleDao.deleteById(id);
	}

}
