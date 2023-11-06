package com.cine.springboot.app.model.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cine.springboot.app.model.dao.IHorarioDao;
import com.cine.springboot.app.model.dao.IPeliculaDao;
import com.cine.springboot.app.model.entity.Horario;
import com.cine.springboot.app.model.entity.Pelicula;

@Service
public class PeliculasService implements IPeliculaService {

	@Autowired
	private IPeliculaDao peliculaDao;
	@Autowired
	private IHorarioService horarioService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	@Override
	public List<Pelicula> listar() {
		// TODO Auto-generated method stub
		return (List<Pelicula>) peliculaDao.findAll();
	}

	@Transactional()
	@Override
	public void guardar(Pelicula pelicula) {
		// TODO Auto-generated method stub
		peliculaDao.save(pelicula);
	}

	@Transactional(readOnly = true)
	@Override
	public Pelicula buscarPorid(int id) {
		// TODO Auto-generated method stub
		return peliculaDao.findById(id).orElse(null);
	}

	@Transactional()
	@Override
	public void borrarFisico(int id) {
		// TODO Auto-generated method stub
		peliculaDao.deleteById(id);
	}

	@Override
	public List<Pelicula> listarPorFecha(Date fecha) {
		List<Pelicula> peliculas = null;
		// Buscamos en la tabla de horarios, [agrupando por idPelicula]
		List<Horario> horarios = horarioService.listarPorFecha(fecha);
		peliculas = new LinkedList<>();

		// Formamos la lista final de Peliculas que regresaremos.
		for (Horario h : horarios) {
			// Solo nos interesa de cada registro de horario, el registro de pelicula.
			peliculas.add(h.getPeliculaTipo().getPelicula());
		}
		return peliculas;

	}

/*@Transactional(readOnly = true)
	@Override
	public List<Horario> buscarPorFecha(Date fecha) {
		// TODO Auto-generated method stub
		return (List<Horario>) horarioDao.findByFecha.findAll();
	}
*/
	public boolean quitarTipo(int idPelicula, int idTipo) {
		String sqlQuery = "delete from tipo_pelicula where id_pelicula= ? and id_tipo = ?";
		return jdbcTemplate.update(sqlQuery, idPelicula, idTipo) > 0;
	}

	public List<Map<String, Object>> listarTiposNoAsig(int id) {
		String xsql = "SELECT *  FROM tipos t1 WHERE NOT EXISTS (SELECT NULL  FROM tipo_pelicula t2  WHERE t2.id_tipo = t1.id and t2.id_pelicula = ? )and t1.estado=true;";
		return this.jdbcTemplate.queryForList(xsql, new Object[] { id });
	}

	public void asignarTipo(int idPelicula, int idTipo) {

		jdbcTemplate.update("insert into tipo_pelicula(id_pelicula, id_tipo) values (?,?)", idPelicula, idTipo);

	}

	@Override
	public List<Map<String, Object>> listarTiposAsig(int id) {
		String xsql = "select * from tipos t , tipo_pelicula tp where tp.id_tipo=t.id and tp.id_pelicula=?;";
		return this.jdbcTemplate.queryForList(xsql, new Object[] { id });
	}

	public void eliminar(int idPelicula) {

		jdbcTemplate.update("update peliculas set estado=false where id=?;", idPelicula);

	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Pelicula> listarPorFechaEstreno(Date fecha) {
		// TODO Auto-generated method stub
		return (List<Pelicula>) peliculaDao.listarPorFechaEstreno(fecha);
	}
}
