package com.cine.springboot.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cine.springboot.app.model.dao.IDetalleDao;
import com.cine.springboot.app.model.dao.IPeliculaDao;
import com.cine.springboot.app.model.entity.Detalle;
import com.cine.springboot.app.model.entity.Pelicula;

@Service
public class DetalleService implements IDetalleService{


	@Autowired
	private IDetalleDao detalleDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly = true)
	@Override
	public List<Detalle> findAll() {
		return (List<Detalle>) detalleDao.findAll();
	}

	@Transactional()
	@Override
	public void save(Detalle detalle) {
		detalleDao.save(detalle);

	}

	@Transactional(readOnly = true)
	@Override
	public Detalle findOne(int id) {
		// TODO Auto-generated method stub
		return detalleDao.findById(id).orElse(null);
	}

	@Transactional()
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		detalleDao.deleteById(id);
	}
	
	

}
