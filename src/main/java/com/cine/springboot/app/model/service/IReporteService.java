package com.cine.springboot.app.model.service;


import java.util.Date;
import java.util.List;

import com.cine.springboot.app.Utils.Reporte;
import com.cine.springboot.app.Utils.Total;
import com.cine.springboot.app.model.entity.Pelicula;

public interface IReporteService {
	public List<Reporte> ListarVentas(Date fecha1,Date fecha2);
	
	public Total calcularTotal(Date fecha1,Date fecha2);
	
	public List<Reporte> ListarVentasPagadas(Date fecha1,Date fecha2);
	
	public List<Reporte> ListarVentasAnuladas(Date fecha1,Date fecha2);
	
	public Total calcularTotalPagadas(Date fecha1,Date fecha2);
	
	public Total calcularTotalAnuladas(Date fecha1,Date fecha2);
	
	public List<Pelicula> ListarPeliculas(Date fecha1,Date fecha2);
	
	public List<Reporte> ListarVentasPorPelicula(Date fecha1,Date fecha2,int idPelicula);
	
	public Total calcularTotalPorPelicula(Date fecha1,Date fecha2,int idPelicula);
	
	public List<Reporte> ListarVentasPagadasPorPelicula(Date fecha1,Date fecha2,int idPelicula);
	
	public Total calcularTotalPagadasPorPelicula(Date fecha1,Date fecha2, int idPelicula);
	
	public List<Reporte> ListarVentasAnuladasPorPelicula(Date fecha1,Date fecha2,int idPelicula);
	
	public Total calcularTotalAnuladasPorPelicula(Date fecha1,Date fecha2,int idPelicula);
}
