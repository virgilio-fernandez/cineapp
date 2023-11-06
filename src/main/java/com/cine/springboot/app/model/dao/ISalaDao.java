package com.cine.springboot.app.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cine.springboot.app.model.entity.Sala;


public interface ISalaDao extends CrudRepository<Sala, Integer> {
	
	@Query("select d from Sala d where d.estado = true")
	public List<Sala> findByEstado();

}
