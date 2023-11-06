package com.cine.springboot.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cine.springboot.app.model.dao.IDetalleVentaDao;

import com.cine.springboot.app.model.entity.DetalleVenta;
import com.cine.springboot.app.model.entity.TipoPelicula;

@Service
public class DetalleVentaService implements IDetalleVentaService {

	@Autowired
	private IDetalleVentaDao detalleVentaDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	@Transactional()
	public void  guardar(int cantidad, double precio, double subtotal, int idDescuento, int idHorario, int idVenta  ) {
		    
	     jdbcTemplate.update("insert into detalle_venta(cantidad,p_unit,subtotal,id_descuento,id_horario,id_venta) values (?,?,?,?,?,?)",cantidad, precio, subtotal, idDescuento,idHorario,idVenta);
	     
	 }
	
	@Transactional()
	@Override
	public boolean borrarDetalles(int id) {
		String sqlQuery = "delete from detalle_venta where id_venta = ?";
		  return jdbcTemplate.update(sqlQuery, id) > 0;
	}
	@Transactional()
	@Override
	public List<DetalleVenta> listarPorId(int idVenta) {
		// TODO Auto-generated method stub
		return (List<DetalleVenta>) detalleVentaDao.listarPorId(idVenta);
	}


}
