package com.cine.springboot.app.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cine.springboot.app.model.entity.Horario;
import com.cine.springboot.app.model.entity.Pelicula;
import com.cine.springboot.app.model.entity.TipoPelicula;

public interface IPeliculaDao extends CrudRepository<Pelicula, Integer> {

	
/*	
	@Query("select p from Pelicula p join fetch p.tipos t where p.id= t.id")
	public List<Pelicula> BuscarPeliculaTipo();
	
	@Query("select p from TipoPelicula p join fetch p.peliculas t where p.id= t.id")
	public List<TipoPelicula> BuscarTipos();
*/
/*	@Query("select t from TipoPelicula t where t.pelicula.id=?1 and t.tipo.id=?2")	
	public List<Pelicula> buscarPorId(int idPelicula, int idTipo);
*/	
	@Query("select d from Pelicula d where d.fechaEstreno>?1 order by d.fechaEstreno asc")
	public List<Pelicula> listarPorFechaEstreno(Date fecha);
}
