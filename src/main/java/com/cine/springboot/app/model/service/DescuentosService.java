package com.cine.springboot.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cine.springboot.app.model.dao.IDescuentoDao;
import com.cine.springboot.app.model.entity.Descuento;

@Service
public class DescuentosService implements IDescuentoService {

	@Autowired
	private IDescuentoDao descuentoDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly = true)
	@Override
	public List<Descuento> listar() {
		// TODO Auto-generated method stub
		return (List<Descuento>) descuentoDao.findAll();
	}

	@Transactional()
	@Override
	public void guardar(Descuento descuento) {
		// TODO Auto-generated method stub
		descuentoDao.save(descuento);
	}

	@Transactional(readOnly = true)
	@Override
	public Descuento buscarPorId(int id) {
		// TODO Auto-generated method stub
		return descuentoDao.findById(id).orElse(null);
	}

	@Transactional()
	@Override
	public void borrarFisico(int id) {
		// TODO Auto-generated method stub
		descuentoDao.deleteById(id);
	}

	@Transactional()
	public void  borrarLogico(int id) {
	    
	     jdbcTemplate.update("update descuentos set estado=false where id=?;",id);
	     
	 }
	@Transactional(readOnly = true)
	@Override
	public List<Descuento> listarActivos() {
		// TODO Auto-generated method stub
		return (List<Descuento>) descuentoDao.listarActivos();
	}
}
