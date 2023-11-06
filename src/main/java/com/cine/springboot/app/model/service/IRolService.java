package com.cine.springboot.app.model.service;

import java.util.List;

import com.cine.springboot.app.model.entity.Rol;

public interface IRolService {
	public List<Rol> findAll();

	public void save(Rol rol);

	public Rol findOne(int id);

	public void delete(int id);
	
//	public List<Rol> buscar(int idusuario, String rol);
	

}
