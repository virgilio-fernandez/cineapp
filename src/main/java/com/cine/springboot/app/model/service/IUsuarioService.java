package com.cine.springboot.app.model.service;

import java.util.List;

import com.cine.springboot.app.model.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario> listar();

	public void guardar(Usuario tipo);

	public Usuario buscarPorId(int id);


	
	public Usuario buscarPorUsername(String username);
	
	public void  eliminar(int id);
	public boolean eliminarRol(int id);

}
