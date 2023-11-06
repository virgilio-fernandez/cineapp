package com.cine.springboot.app.model.dao;

import org.springframework.data.repository.CrudRepository;


import com.cine.springboot.app.model.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Integer> {
	
	public Cliente findByNit(String nit);

}
