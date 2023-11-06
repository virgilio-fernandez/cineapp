package com.cine.springboot.app.model.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cine.springboot.app.model.entity.Pelicula;

public interface IPeliculaService {
	public List<Pelicula> listar();

	public void guardar(Pelicula pelicula);

	public Pelicula buscarPorid(int id);

	public void borrarFisico(int id);

	public List<Pelicula> listarPorFecha(Date fecha);

	/*
	 * public List<Pelicula> BuscarPeliculaTipo();
	 * 
	 * public List<TipoPelicula> BuscarTipos();
	 */
	public boolean quitarTipo(int idPelicula, int idTipo);

	public List<Map<String, Object>> listarTiposNoAsig(int id);
	public List<Map<String, Object>> listarTiposAsig(int id);

	public void asignarTipo(int idPelicula, int idTipo);
	
	public void  eliminar(int idPelicula);
	
	public List<Pelicula> listarPorFechaEstreno(Date fecha);
	
	
	
}
