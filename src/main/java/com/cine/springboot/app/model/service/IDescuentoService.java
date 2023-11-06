package com.cine.springboot.app.model.service;

import java.util.List;


import com.cine.springboot.app.model.entity.Descuento;

public interface IDescuentoService {
	public List<Descuento> listar();

	public void guardar(Descuento usuario);

	public Descuento buscarPorId(int id);

	public void borrarFisico(int id);
	
	public void borrarLogico(int id);
	
	public List<Descuento> listarActivos();


}
