package com.cine.springboot.app.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cine.springboot.app.model.entity.TipoPelicula;
import com.cine.springboot.app.model.entity.Pelicula;

public interface ITipoPeliculaDao extends CrudRepository<TipoPelicula, Integer> {
	@Query("select t from TipoPelicula t where t.pelicula.id=?1 and t.tipo.id=?2")	
	public TipoPelicula buscarPorId(int idPelicula, int idTipo);
	
	@Query("select t from TipoPelicula t where t.pelicula.id=?1")	 
	public List<TipoPelicula> listarPorId(int idPelicula);
	
	//@Query("select t from TipoPelicula t where t.pelicula.id=?1 and t.tipo.id=?2")	
//	@Query("select t from TipoPelicula t join fetch Pelicula p where t.pelicula.id=?1 and t.tipo.id=?2 and t.pelicula.id=p.id and t.pelicula.estado= true") 
	@Query("select t from TipoPelicula t where t.pelicula.estado=true")
	public List<TipoPelicula>listarTiposPeliculaActiva();
	
} 
