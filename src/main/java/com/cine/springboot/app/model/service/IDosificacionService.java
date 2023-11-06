package com.cine.springboot.app.model.service;

import java.util.List;


import com.cine.springboot.app.model.entity.Dosificacion;


public interface IDosificacionService {
	
	public List<Dosificacion> findAll();

	public void save(Dosificacion dosificacion);

	public Dosificacion findOne(int id);



	public List<Dosificacion> buscarDosificacion();


}
