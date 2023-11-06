package com.cine.springboot.app.model.service;

import java.util.List;

import com.cine.springboot.app.model.entity.Detalle;

public interface IDetalleService {
	public List<Detalle> findAll();

	public void save(Detalle detalle);

	public Detalle findOne(int id);

	public void delete(int id);
}
