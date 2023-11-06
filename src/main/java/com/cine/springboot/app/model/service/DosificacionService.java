package com.cine.springboot.app.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cine.springboot.app.model.dao.IDosificacionDao;
import com.cine.springboot.app.model.entity.Dosificacion;
import com.cine.springboot.app.model.entity.Factura;

@Service
public class DosificacionService implements IDosificacionService {


	@Autowired
	private IDosificacionDao dosificacionDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly = true)
	@Override
	public List<Dosificacion> findAll() {
		return (List<Dosificacion>) dosificacionDao.findAll();
	}

	@Transactional()
	@Override
	public void save(Dosificacion dosificacion) {
		dosificacionDao.save(dosificacion);

	}

	@Transactional(readOnly = true)
	@Override
	public Dosificacion findOne(int id) {
		// TODO Auto-generated method stub
		return dosificacionDao.findById(id).orElse(null);
	}

	

	@Transactional(readOnly = true)
	public List<Dosificacion> buscarDosificacion(){
		
		
		return (List<Dosificacion>) dosificacionDao.findByEstado();
	}
	
	
	
}
