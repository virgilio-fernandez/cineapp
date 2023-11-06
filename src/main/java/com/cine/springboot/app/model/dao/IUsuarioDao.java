package com.cine.springboot.app.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cine.springboot.app.model.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Integer>{
	public Usuario findByUsername(String username);
	
	
	
}
