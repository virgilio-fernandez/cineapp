package com.cine.springboot.app.model.service;

import java.util.List;

import com.cine.springboot.app.model.entity.TipoPelicula;

public interface ITipoPeliculaService {
	public List<TipoPelicula> findAll();

	public void save(TipoPelicula pelicula);

	public TipoPelicula findOne(int id);

	public void delete(int id);
	
	public TipoPelicula buscarPorId(int idPelicula, int idTipo);
	
	public List<TipoPelicula> listarPorId(int idPelicula);
	
	public List<TipoPelicula> listarPorPeliculaActiva();
	

}
