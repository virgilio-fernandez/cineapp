package com.cine.springboot.app.model.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cine.springboot.app.model.dao.InformacionDao;
import com.cine.springboot.app.model.entity.InformacionCine;

@Service
public class InformacionService implements InformacionCineService {

	@Autowired
	private InformacionDao informacionDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<InformacionCine> listar() {
		// TODO Auto-generated method stub
		return (List<InformacionCine>) informacionDao.findAll();
	}

	@Transactional()
	@Override
	public void guardar(InformacionCine info) {
		// TODO Auto-generated method stub
		informacionDao.save(info);
	}

	@Transactional(readOnly = true)
	@Override
	public InformacionCine buscarPorId(int id) {
		// TODO Auto-generated method stub
		return informacionDao.findById(id).orElse(null);
	}

	/*@Transactional()
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		informacionDao.deleteById(id);
	}
*/
}
