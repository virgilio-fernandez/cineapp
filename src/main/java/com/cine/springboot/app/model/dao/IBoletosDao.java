package com.cine.springboot.app.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cine.springboot.app.model.entity.Boleto;


public interface IBoletosDao extends CrudRepository<Boleto, Integer> {
	
	@Query("select t from Boleto t where t.detalleVenta.venta.id=?1")	 
	public List<Boleto> listarPorId(int idVenta);

}
