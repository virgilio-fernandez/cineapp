package com.cine.springboot.app.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cine.springboot.app.model.entity.InformacionCine;

public interface InformacionDao extends CrudRepository<InformacionCine, Integer> {
	
}
