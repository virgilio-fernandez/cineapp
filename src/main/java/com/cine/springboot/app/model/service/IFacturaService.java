package com.cine.springboot.app.model.service;



import java.util.Date;

import com.cine.springboot.app.model.entity.Factura;
import com.cine.springboot.app.model.entity.Venta;


public interface IFacturaService {
	
	public void save(Factura factura);
	
	public void  guardar(int idDosificacion, int idVenta, Date fecha, int numero, int idInformacion );
	
//	public Factura buscarFacturaPorVenta(int idVenta);
	
	public Factura buscarFacturaPorVenta(int venta);

}
