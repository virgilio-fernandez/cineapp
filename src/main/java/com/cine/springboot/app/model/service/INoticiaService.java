package com.cine.springboot.app.model.service;

import java.util.List;

import com.cine.springboot.app.model.entity.Noticia;

public interface INoticiaService {
	public List<Noticia> listar();

	public Noticia buscarPorId(int id);

	public void borrar(int id);

	public void guardar(Noticia noticia);
	
	public List<Noticia> listarActivas();
}
