package com.cine.springboot.app.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cine.springboot.app.model.dao.IUsuarioDao;
import com.cine.springboot.app.model.entity.Usuario;


@Service
public class UsuarioService implements IUsuarioService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@Transactional(readOnly = true)
	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Transactional()
	@Override
	public void guardar(Usuario tipo) {
		// TODO Auto-generated method stub
		usuarioDao.save(tipo);
	}

	@Transactional(readOnly = true)
	@Override
	public Usuario buscarPorId(int id) {
		// TODO Auto-generated method stub
		return usuarioDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public Usuario buscarPorUsername(String username) {
		
		return usuarioDao.findByUsername(username);
	}
	
	@Transactional()
	public void  eliminar(int id) {
	    
	     jdbcTemplate.update("update usuarios set estado=false where id=?;",id);
	     
	 }	
	@Transactional()
	@Override
	public boolean eliminarRol(int id) {
		String sqlQuery = "delete from roles where id = ?";
		  return jdbcTemplate.update(sqlQuery, id) > 0;
	}
	
}
