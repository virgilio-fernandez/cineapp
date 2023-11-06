package com.cine.springboot.app.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cine.springboot.app.model.entity.Dosificacion;

public interface IDosificacionDao extends CrudRepository<Dosificacion, Integer> {
	
	@Query("select d from Dosificacion d where d.estado = true")
	public List<Dosificacion> findByEstado();

}
