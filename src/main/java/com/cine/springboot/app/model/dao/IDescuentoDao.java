package com.cine.springboot.app.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cine.springboot.app.model.entity.Descuento;


public interface IDescuentoDao extends CrudRepository<Descuento, Integer> {
	
	//@Query("select t from Descuento t where t.estado=true and t.nombre!='Ninguno'")	 
	@Query("select t from Descuento t where t.estado=true ")	 
	public List<Descuento> listarActivos();

}
