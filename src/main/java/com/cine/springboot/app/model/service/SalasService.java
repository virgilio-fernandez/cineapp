package com.cine.springboot.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cine.springboot.app.model.dao.ISalaDao;
import com.cine.springboot.app.model.entity.Pelicula;
import com.cine.springboot.app.model.entity.Sala;

@Service
public class SalasService implements ISalaService {
	@Autowired
	private ISalaDao salaDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	@Override
	public List<Sala> listar() {
		// TODO Auto-generated method stub
		return (List<Sala>) salaDao.findAll();
	}

	@Transactional()
	@Override
	public void guardar(Sala sala) {
		// TODO Auto-generated method stub
		salaDao.save(sala);
	}

	@Transactional(readOnly = true)
	@Override
	public Sala buscarPorId(int id) {
		// TODO Auto-generated method stub
		return salaDao.findById(id).orElse(null);
	}

	@Transactional()
	@Override
	public void borrarFisico(int id) {
		// TODO Auto-generated method stub
		salaDao.deleteById(id);
	}
	@Transactional()
	public void  borrarLogico(int id) {
	    
	     jdbcTemplate.update("update salas set estado=false where id=?;",id);
	     
	 }
	
	@Transactional(readOnly = true)
	@Override
	public List<Sala> listarActivas() {
		// TODO Auto-generated method stub
		return (List<Sala>) salaDao.findByEstado();
	}
}
