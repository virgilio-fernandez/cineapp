package com.cine.springboot.app.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cine.springboot.app.model.entity.Rol;
import com.cine.springboot.app.model.entity.TipoPelicula;


public interface IRolDao extends CrudRepository<Rol, Integer> {
	
	/*@Query("INSERT INTO roles (rol, user_id) VALUES (?1,?2)")	
	public Rol insertar(String rol,int idUsuario);
	
*/
	// Horarios por idPelicula (Pelicula.id = Pelicula_Id and fecha=?)
	//public List<Rol> findByIdAndRol(int idUsuario,String rol);
	@Query("select r from Rol r where r.id=?1")	
	public List<Rol> buscarId(int idUsuario);
	
	@Query("delete from Rol where id=?1")	
	public void borrar(int idusuario);
}
