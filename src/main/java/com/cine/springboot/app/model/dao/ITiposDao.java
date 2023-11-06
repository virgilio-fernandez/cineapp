package com.cine.springboot.app.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cine.springboot.app.model.entity.Tipo;

public interface ITiposDao extends CrudRepository<Tipo, Integer>{
	
}
