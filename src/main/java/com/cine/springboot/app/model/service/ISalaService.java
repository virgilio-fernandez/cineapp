package com.cine.springboot.app.model.service;

import java.util.List;

import com.cine.springboot.app.model.entity.Sala;

public interface ISalaService {
	public List<Sala> listar();

	public void guardar(Sala pelicula);

	public Sala buscarPorId(int id);

	public void borrarFisico(int id);
	public void  borrarLogico(int id);
	
	public List<Sala> listarActivas();

}
