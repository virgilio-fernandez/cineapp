package com.cine.springboot.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.cine.springboot.app.model.entity.Venta;

public interface IVentasDao extends CrudRepository<Venta, Integer> {

}
