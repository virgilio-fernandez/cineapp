package com.cine.springboot.app.model.service;

import java.util.List;

import com.cine.springboot.app.model.entity.InformacionCine;


public interface InformacionCineService {
	
	public void guardar(InformacionCine info);

	public InformacionCine buscarPorId(int id);
	
	public List<InformacionCine> listar();


}
