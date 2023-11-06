package com.cine.springboot.app.model.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cine.springboot.app.model.dao.ITipoPeliculaDao;
import com.cine.springboot.app.model.entity.Pelicula;
import com.cine.springboot.app.model.entity.TipoPelicula;
@Service
public class TipoPeliculaService implements ITipoPeliculaService {
	@Autowired
	private ITipoPeliculaDao tipoPeliculaDao;
	
	@Autowired
	private IPeliculaService peliculaService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Transactional(readOnly = true)
	@Override
	public List<TipoPelicula> findAll() {
		// TODO Auto-generated method stub
		return (List<TipoPelicula>) tipoPeliculaDao.findAll();
	}

	@Transactional()
	@Override
	public void save(TipoPelicula tipo) {
		// TODO Auto-generated method stub
		tipoPeliculaDao.save(tipo);
	}

	@Transactional(readOnly = true)
	@Override
	public TipoPelicula findOne(int id) {
		// TODO Auto-generated method stub
		return tipoPeliculaDao.findById(id).orElse(null);
	}

	@Transactional()
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		tipoPeliculaDao.deleteById(id);
	}
	@Transactional(readOnly = true)
	@Override
	public TipoPelicula buscarPorId(int idPelicula, int idTipo) {
		// TODO Auto-generated method stub
		return tipoPeliculaDao.buscarPorId(idPelicula, idTipo);
	}

	@Override
	public List<TipoPelicula> listarPorId(int idPelicula) {
		// TODO Auto-generated method stub
		return (List<TipoPelicula>) tipoPeliculaDao.listarPorId(idPelicula);
	}

	@Transactional(readOnly = true)
	@Override
	public List<TipoPelicula> listarPorPeliculaActiva() {
		// TODO Auto-generated method stub
		return (List<TipoPelicula>) tipoPeliculaDao.listarTiposPeliculaActiva();
	}

	
	
	
	/*public List<TipoPelicula> listarMaterias(int id){
		String xsql="";	
		Pelicula pelicula = peliculaService.findOne(id); 
		
		List tp = this.jdbcTemplate.query(xsql, 
			new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException{
					TipoPelicula tp2 =new TipoPelicula();
					tp2.setPelicula(pelicula);
					tp2.setTipo(tipo);
					return tp2;
				}
			},new Object[] {id});				
		return tp;
	}
	
*/


}
