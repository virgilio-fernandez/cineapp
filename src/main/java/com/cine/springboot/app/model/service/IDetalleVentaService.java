package com.cine.springboot.app.model.service;

import java.util.List;

import com.cine.springboot.app.model.entity.DetalleVenta;

public interface IDetalleVentaService {
	public void  guardar(int cantidad, double precio, double subtotal, int idDescuento, int idHorario, int idVenta  );
	
	public boolean borrarDetalles(int id);
	
	public List<DetalleVenta> listarPorId(int idVenta);

}
