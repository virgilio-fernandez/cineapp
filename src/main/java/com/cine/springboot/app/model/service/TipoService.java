package com.cine.springboot.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cine.springboot.app.model.dao.ITiposDao;
import com.cine.springboot.app.model.entity.Tipo;

@Service
public class TipoService implements ITipoService{
	@Autowired
	private ITiposDao tipoDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly = true)
	@Override
	public List<Tipo> listar() {
		// TODO Auto-generated method stub
		return (List<Tipo>) tipoDao.findAll();
	}

	@Transactional()
	@Override
	public void guardar(Tipo tipo) {
		// TODO Auto-generated method stub
		tipoDao.save(tipo);
	}

	@Transactional(readOnly = true)
	@Override
	public Tipo buscarPorId(int id) {
		// TODO Auto-generated method stub
		return tipoDao.findById(id).orElse(null);
	}

	@Transactional()
	@Override
	public void borrarFisico(int id) {
		// TODO Auto-generated method stub
		tipoDao.deleteById(id);
	}
	@Transactional()
	public void  borrarLogico(int id) {
	    
	     jdbcTemplate.update("update tipos set estado=false where id=?;",id);
	     
	 }

	
}
