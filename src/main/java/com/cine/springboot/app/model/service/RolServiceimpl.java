package com.cine.springboot.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cine.springboot.app.model.dao.IRolDao;
import com.cine.springboot.app.model.entity.Rol;
import com.cine.springboot.app.model.entity.Rol;

@Service
public class RolServiceimpl implements IRolService {
	@Autowired
	private IRolDao rolDao;

	@Transactional(readOnly = true)
	@Override
	public List<Rol> findAll() {
		// TODO Auto-generated method stub
		return (List<Rol>) rolDao.findAll();
	}

	@Transactional()
	@Override
	public void save(Rol rol) {
		// TODO Auto-generated method stub
		rolDao.save(rol);
	}

	@Transactional(readOnly = true)
	@Override
	public Rol findOne(int id) {
		// TODO Auto-generated method stub
		return rolDao.findById(id).orElse(null);
	}

	@Transactional()
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		rolDao.deleteById(id);
	}

	/*@Transactional(readOnly = true)
	@Override
	public List<Rol> buscar(int idusuario, String rol) {
		// TODO Auto-generated method stub
		return (List<Rol>) rolDao.findByIdAndRol(idusuario, rol);
	}
*/	

}
