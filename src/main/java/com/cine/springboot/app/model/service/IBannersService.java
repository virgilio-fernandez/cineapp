package com.cine.springboot.app.model.service;

import java.util.List;

import com.cine.springboot.app.model.entity.Banner;


public interface IBannersService {

	public void guardar(Banner banner); 
	public List<Banner> listar();
	public List<Banner> listarActivos();
	public void eliminar(int idBanner);
	Banner buscarPorId(int idBanner);
}
