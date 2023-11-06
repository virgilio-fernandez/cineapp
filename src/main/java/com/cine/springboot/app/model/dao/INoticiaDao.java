package com.cine.springboot.app.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cine.springboot.app.model.entity.Dosificacion;
import com.cine.springboot.app.model.entity.Noticia;


public interface INoticiaDao extends CrudRepository<Noticia, Integer> {
	@Query("select d from Noticia d where d.estado = true")
	public List<Noticia> listarActivas();

}
