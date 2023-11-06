package com.cine.springboot.app.model.service;

import java.util.Date;
import java.util.List;

import com.cine.springboot.app.model.entity.Horario;

public interface IHorarioService {
	public List<Horario> listar() ;

	public void guardar(Horario horario);

	public Horario buscarPorId(int id);

	public void borrarFisico(int id);
	
	public void  borrarLogico(int id);
	
	public List<Horario> listarActivos() ;
	
	public List<Horario> listarPorFechaSala(Date fecha,int idSala) ;
	
	public List<Horario> listarPorFecha(Date fecha) ;
	
	public List<Horario> listarPorPeliculaFecha(Date fecha,int idPelicula);

	
	
	
//	List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha);
//	Horario buscarPorId(int idHorario);


}
