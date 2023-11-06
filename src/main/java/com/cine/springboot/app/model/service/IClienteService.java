package com.cine.springboot.app.model.service;

import java.util.List;

import com.cine.springboot.app.model.entity.Cliente;


public interface IClienteService {
	public List<Cliente> listar();

	public void guardar(Cliente cliente);

	public Cliente buscarPorId(int id);

	public void borrarFisico(int id);
	
	public Cliente buscarPorNit(String nit);
	public void  eliminar(int id);

}
