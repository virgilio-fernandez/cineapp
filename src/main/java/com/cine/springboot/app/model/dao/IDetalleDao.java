package com.cine.springboot.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.cine.springboot.app.model.entity.Detalle;

public interface IDetalleDao extends CrudRepository< Detalle, Integer> {

}
