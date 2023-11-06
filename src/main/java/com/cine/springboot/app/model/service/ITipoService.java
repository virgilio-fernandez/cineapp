package com.cine.springboot.app.model.service;

import java.util.List;

import com.cine.springboot.app.model.entity.Tipo;

public interface ITipoService {
	public List<Tipo> listar();

	public void guardar(Tipo tipo);

	public Tipo buscarPorId(int id);

	public void borrarFisico(int id);

	public void  borrarLogico(int id);
}
