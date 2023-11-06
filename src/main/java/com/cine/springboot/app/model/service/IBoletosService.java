package com.cine.springboot.app.model.service;

import java.util.List;

import com.cine.springboot.app.model.entity.Boleto;

public interface IBoletosService {
	
	public List<Boleto> listarPorId(int idVenta);

	

}
