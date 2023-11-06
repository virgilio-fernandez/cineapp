package com.cine.springboot.app.model.service;

import java.util.Date;
import java.util.List;

import com.cine.springboot.app.model.entity.Dosificacion;
import com.cine.springboot.app.model.entity.Factura;
import com.cine.springboot.app.model.entity.Venta;

public interface IVentaService {
	public List<Venta> listar();

	public void guardar(Venta venta);

	public Venta buscarPorId(int id);


	public Venta buscarVenta();
	

	public Factura buscarFactura(int idDosificacion);
	
	public void  anular(int id);

	void adicionarBoleto(Date fecha, String hora, int idDetalle, int idHorario);
	
	public void borrarFisico(int id);
}
