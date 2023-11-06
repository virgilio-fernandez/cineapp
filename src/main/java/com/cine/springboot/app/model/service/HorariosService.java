package com.cine.springboot.app.model.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cine.springboot.app.model.dao.IHorarioDao;
import com.cine.springboot.app.model.dao.IPeliculaDao;
import com.cine.springboot.app.model.entity.Horario;
import com.cine.springboot.app.model.entity.Pelicula;



@Service
public class HorariosService implements IHorarioService{
	@Autowired
	private IHorarioDao horarioDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@Transactional(readOnly = true)
	@Override
	public List<Horario> listar() {
		// TODO Auto-generated method stub
		return (List<Horario>) horarioDao.findAll();
	}

	@Transactional()
	@Override
	public void guardar(Horario horario) {
		// TODO Auto-generated method stub
		horarioDao.save(horario);
	}

	@Transactional(readOnly = true)
	@Override
	public Horario buscarPorId(int id) {
		// TODO Auto-generated method stub
		return horarioDao.findById(id).orElse(null);
	}

	@Transactional()
	@Override
	public void borrarFisico(int id) {
		// TODO Auto-generated method stub
		horarioDao.deleteById(id);
	}


	@Transactional()
	public void  borrarLogico(int id) {
	    
	     jdbcTemplate.update("update horarios set estado=false where id=?;",id);
	     
	 }
	
	@Transactional(readOnly = true)
	@Override
	public List<Horario> listarActivos() {
		// TODO Auto-generated method stub
		return (List<Horario>) horarioDao.findByEstado();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Horario> listarPorFechaSala(Date fecha,int idSala) {
		// TODO Auto-generated method stub
		return (List<Horario>) horarioDao.findByFechaAndSala(fecha, idSala);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Horario> listarPorFecha(Date fecha) {
		// TODO Auto-generated method stub
		return (List<Horario>) horarioDao.findByFecha(fecha);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Horario> listarPorPeliculaFecha(Date fecha, int idPelicula) {
		// TODO Auto-generated method stub
		return (List<Horario>) horarioDao.listarPorPeliculaFecha(fecha, idPelicula);
	}

}
