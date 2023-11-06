package com.cine.springboot.app.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cine.springboot.app.model.entity.Cliente;
import com.cine.springboot.app.model.entity.DetalleVenta;
import com.cine.springboot.app.model.entity.Factura;

import com.cine.springboot.app.model.entity.Venta;

public interface IFacturaDao extends CrudRepository<Factura, Integer> {
	
	@Query("select t from Factura t where t.venta.id=?1")	 
	public Factura buscarPorId(int idVenta);

}
