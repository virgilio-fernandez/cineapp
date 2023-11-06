package com.cine.springboot.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cine.springboot.app.Utils.Reporte;
import com.cine.springboot.app.Utils.Total;
import com.cine.springboot.app.model.entity.Descuento;
import com.cine.springboot.app.model.entity.Pelicula;
import com.cine.springboot.app.model.service.IReporteService;

@Controller
@RequestMapping(value = "/reportes")
public class ReportesController {

	@Autowired
	private IReporteService reporteService;

	@GetMapping("/reporteGeneral")
	public String reporteGeneral(Model model) {

		model.addAttribute("titulo", "Listado  de Ventas");

		return "reportes/reporteGeneral";

	}

	/*@GetMapping("/reportePelicula")
	public String reportePelicula(Model model) {
		System.out.println("dentro servlet salas");
		model.addAttribute("titulo", "Reportes");

		return "reportes/reportePelicula";

	}
*/

	@RequestMapping(value = "/imprimirReporte", produces = "application/pdf")
	public String imprimir(HttpServletRequest req, Map<String, Object> model, RedirectAttributes flash)
			throws ParseException {
		String filtro = req.getParameter("filtro");
		String fechas = req.getParameter("date_range");
		String pelicula = req.getParameter("pelicula");
		System.out.println(filtro + "  datos  " + fechas);

		String[] parts = fechas.split(" / ");
		String f1 = parts[0];
		String f2 = parts[1];

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha1 = sdf.parse(f1);
		Date fecha2 = sdf.parse(f2);
		
		model.put("f1", f1);
		model.put("f2", f2);

		List<Reporte> list = null;
		Total total = null;
		if (pelicula.equals("todas")) {
			if (filtro.equals("todas")) {
				list = reporteService.ListarVentas(fecha1, fecha2);
				total = reporteService.calcularTotal(fecha1, fecha2);
			}
			if (filtro.equals("sinAnular")) {
				list = reporteService.ListarVentasPagadas(fecha1, fecha2);
				total = reporteService.calcularTotalPagadas(fecha1, fecha2);
			}
			if (filtro.equals("anuladas")) {
				list = reporteService.ListarVentasAnuladas(fecha1, fecha2);
				total = reporteService.calcularTotalAnuladas(fecha1, fecha2);
			}
			
		}else {
			if (filtro.equals("todas")) {
				list = reporteService.ListarVentasPorPelicula(fecha1, fecha2,Integer.parseInt(pelicula));
				total = reporteService.calcularTotalPorPelicula(fecha1, fecha2,Integer.parseInt(pelicula));
			}
			if (filtro.equals("sinAnular")) {
				list = reporteService.ListarVentasPagadasPorPelicula(fecha1, fecha2,Integer.parseInt(pelicula));
				total = reporteService.calcularTotalPagadasPorPelicula(fecha1, fecha2,Integer.parseInt(pelicula));
			}
			if (filtro.equals("anuladas")) {
				list = reporteService.ListarVentasAnuladasPorPelicula(fecha1, fecha2,Integer.parseInt(pelicula));
				total = reporteService.calcularTotalAnuladasPorPelicula(fecha1, fecha2,Integer.parseInt(pelicula));
			}
			
		}
		
		
	
	
		System.out.println(list.size());
		
	
		model.put("lista", list);
		model.put("totales", total);
		
		return "ventas/imprimirReporteGeneral";
	}
	
	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listarVentas(Map<String, Object> model, @RequestParam("fechas") String fechas, @RequestParam("filtro") String filtro) throws ParseException {
		System.out.println("filtro: "+filtro);
		String[] parts = fechas.split(" / ");
		String f1 = parts[0];
		String f2 = parts[1];

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha1 = sdf.parse(f1);
		Date fecha2 = sdf.parse(f2);
		
		List<Reporte> list = null;
		Total total = null;
		if (filtro.equals("todas")) {
			list = reporteService.ListarVentas(fecha1, fecha2);
			total = reporteService.calcularTotal(fecha1, fecha2);
		}
		if (filtro.equals("sinAnular")) {
			list = reporteService.ListarVentasPagadas(fecha1, fecha2);
			total = reporteService.calcularTotalPagadas(fecha1, fecha2);
		}
		if (filtro.equals("anuladas")) {
			list = reporteService.ListarVentasAnuladas(fecha1, fecha2);
			total = reporteService.calcularTotalAnuladas(fecha1, fecha2);
		}
		
		System.out.println(list.size());
		
		
		model.put("lista", list);
		model.put("total", total);
		
		//mostrando peliculas vendidas en el rango de fechas
		
		List<Pelicula> listaPeliculas = reporteService.ListarPeliculas(fecha1, fecha2);
		
		
		model.put("listaPeliculas", listaPeliculas);
		
		
		
	    return "reportes/listar :: resultsList";
	}
	
	@RequestMapping(value = "/listarPorPelicula", method = RequestMethod.GET)
	public String listarPorPelicula(Map<String, Object> model, @RequestParam("fechas") String fechas, @RequestParam("filtro") String filtro, @RequestParam("idPelicula") int idPelicula) throws ParseException {
		System.out.println("filtro: "+filtro);
		String[] parts = fechas.split(" / ");
		String f1 = parts[0];
		String f2 = parts[1];

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha1 = sdf.parse(f1);
		Date fecha2 = sdf.parse(f2);
		
		List<Reporte> list = null;
		Total total = null;
		if (filtro.equals("todas")) {
			list = reporteService.ListarVentasPorPelicula(fecha1, fecha2,idPelicula);
			total = reporteService.calcularTotalPorPelicula(fecha1, fecha2,idPelicula);
		}
		if (filtro.equals("sinAnular")) {
			list = reporteService.ListarVentasPagadasPorPelicula(fecha1, fecha2,idPelicula);
			total = reporteService.calcularTotalPagadasPorPelicula(fecha1, fecha2,idPelicula);
		}
		if (filtro.equals("anuladas")) {
			list = reporteService.ListarVentasAnuladasPorPelicula(fecha1, fecha2,idPelicula);
			total = reporteService.calcularTotalAnuladasPorPelicula(fecha1, fecha2,idPelicula);
		}
		
		System.out.println(list.size());
		
		
		model.put("lista", list);
		model.put("total", total);
		
		
		
	    return "reportes/listarPorPelicula :: resultsList";
	}

}
