package com.cine.springboot.app.model.dao;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cine.springboot.app.model.entity.Horario;

public interface IHorarioDao extends CrudRepository<Horario, Integer> {

	@Query("select d from Horario d where d.estado = true")
	public List<Horario> findByEstado();
	
	
	@Query("select d from Horario d where d.fecha=?1 and d.sala.id=?2")
	public List<Horario> findByFechaAndSala(Date fecha, int idSala);
	
/*	@Query("select h from Horario h where h.fecha = :fecha and h.peliculaTipo.pelicula.estado=true group by h.peliculaTipo.pelicula order by pelicula.id asc")
	public List<Horario> findByFecha(@Param("fecha") Date fecha);
*/	
	@Query("select d from Horario d where d.fecha=?1 and d.peliculaTipo.pelicula.estado=true  order by d.peliculaTipo.pelicula.id asc")
	public List<Horario> findByFecha(Date fecha);
	
	@Query("select d from Horario d where d.fecha=?1 and d.peliculaTipo.pelicula.id=?2 order by d.hora asc")
	public List<Horario> listarPorPeliculaFecha(Date fecha,int idPelicula);
}
