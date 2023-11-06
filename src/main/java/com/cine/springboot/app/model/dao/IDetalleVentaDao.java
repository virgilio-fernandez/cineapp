package com.cine.springboot.app.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cine.springboot.app.model.entity.DetalleVenta;
import com.cine.springboot.app.model.entity.Dosificacion;
import com.cine.springboot.app.model.entity.TipoPelicula;

public interface IDetalleVentaDao extends CrudRepository<DetalleVenta, Integer> {
	
	@Query("select t from DetalleVenta t where t.venta.id=?1")	 
	public List<DetalleVenta> listarPorId(int idVenta);

}
