package com.cine.springboot.app.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cine.springboot.app.Utils.Reporte;
import com.cine.springboot.app.Utils.Total;
import com.cine.springboot.app.Utils.n2t;
import com.cine.springboot.app.model.entity.Boleto;
import com.cine.springboot.app.model.entity.Cliente;
import com.cine.springboot.app.model.entity.Descuento;
import com.cine.springboot.app.model.entity.DetalleVenta;
import com.cine.springboot.app.model.entity.Dosificacion;
import com.cine.springboot.app.model.entity.Factura;
import com.cine.springboot.app.model.entity.Horario;
import com.cine.springboot.app.model.entity.InformacionCine;
import com.cine.springboot.app.model.entity.Usuario;
import com.cine.springboot.app.model.entity.Venta;
import com.cine.springboot.app.model.service.IBoletosService;
import com.cine.springboot.app.model.service.IClienteService;
import com.cine.springboot.app.model.service.IDescuentoService;
import com.cine.springboot.app.model.service.IDetalleVentaService;
import com.cine.springboot.app.model.service.IDosificacionService;
import com.cine.springboot.app.model.service.IFacturaService;
import com.cine.springboot.app.model.service.IHorarioService;
import com.cine.springboot.app.model.service.IUsuarioService;
import com.cine.springboot.app.model.service.IVentaService;
import com.cine.springboot.app.model.service.InformacionService;

@Controller
@RequestMapping(value = "/ventas")
public class VentasController {

	@Autowired
	private IVentaService ventaService;
	@Autowired
	private IHorarioService horarioService;

	@Autowired
	private IDescuentoService descuentoService;

	@Autowired
	private IClienteService clienteService;
	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IDetalleVentaService detalleVentaService;

	@Autowired
	private IDosificacionService dosificacionService;

	@Autowired
	private IFacturaService facturaService;

	@Autowired
	private InformacionService infoService;
	
	@Autowired
	private IBoletosService boletoService; 

	@GetMapping("/listar")
	public String listar(Model model) {
		System.out.println("dentro servlet ventas");
		model.addAttribute("titulo", "Listado de Ventas");
		model.addAttribute("ventas", ventaService.listar());
		return "ventas/listVentas";

	}

	@RequestMapping(value = "/form")
	public String adicionar(Map<String, Object> model, RedirectAttributes flash) {
		Venta venta = new Venta();
		model.put("venta", venta);
		model.put("horarios", horarioService.listarActivos());
		model.put("descuentos", descuentoService.listarActivos());

		List<InformacionCine> info = infoService.listar();
		if (info.size() == 0) {

			flash.addFlashAttribute("error", "Error adicionar informacion del negocio!");
			return "redirect:/ventas/listar";
		}
		List<Dosificacion> listaDosificacion = dosificacionService.buscarDosificacion();
		if (listaDosificacion.size() > 1) {

			flash.addFlashAttribute("error", "Error debe existir solo una dosificacion activa!");
			return "redirect:/ventas/listar";

		}
		if (listaDosificacion.size() == 0) {

			flash.addFlashAttribute("error", "Error no existe ninguna dosificacion activa!");
			return "redirect:/ventas/listar";

		}
		model.put("titulo", "Adicionar Venta");
		return "ventas/formVenta";
	}

	@GetMapping(value = "/buscarHorario/{term}", produces = { "application/json" })
	public @ResponseBody Horario buscarHorario(@PathVariable String term) {
		Horario horario = horarioService.buscarPorId(Integer.parseInt(term));
		return horario;
	}

	@GetMapping(value = "/buscarDescuento/{term}", produces = { "application/json" })
	public @ResponseBody Descuento buscarDescuento(@PathVariable String term) {
		return descuentoService.buscarPorId(Integer.parseInt(term));
	}

	@GetMapping(value = "/buscarCliente/{term}", produces = { "application/json" })
	public @ResponseBody Cliente buscarCliente(@PathVariable String term) {
		Cliente cli = clienteService.buscarPorNit(term);
		System.out.println(cli);
		return cli;
	}

	@GetMapping(value = "/guardarCliente", produces = { "application/json" })
	public @ResponseBody Cliente guardarCliente(HttpServletRequest req) {
		String nit = req.getParameter("add-nit");
		String nombre = req.getParameter("add-nombre");
		String apellido1 = req.getParameter("add-apellido1");
		String apellido2 = req.getParameter("add-apellido2");
		// String id=req.getParameter("add-id");
		Cliente cli = new Cliente();
		// cli.setId(Integer.parseInt(id));
		cli.setNit(nit);
		cli.setNombre(nombre);
		cli.setApellido1(apellido1);
		cli.setApellido2(apellido2);

		Date date = new Date();
		cli.setFecha(date);
		clienteService.guardar(cli);
		// System.out.println(cli);
		return cli;
	}

	@PostMapping("/save")
	public String guardar(Venta venta, RedirectAttributes flash, HttpServletRequest req,
			Authentication authentication) {
		// verificar si existe la informacion del negocio para la factura

		List<InformacionCine> info = infoService.listar();

		InformacionCine infoCine = infoService.buscarPorId(info.get(0).getId());

		System.out.println(venta.getCliente().getNit());
		System.out.println(venta.getTotal());
		Date date = new Date();
		// Caso 1: obtener la hora y salida por pantalla con formato:
		DateFormat hourFormat = new SimpleDateFormat("HH:mm");
		System.out.println("Hora: " + hourFormat.format(date));
		// Caso 2: obtener la fecha y salida por pantalla con formato:
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Fecha: " + dateFormat.format(date));
		venta.setHora(hourFormat.format(date));
		venta.setFecha(date);
		Usuario user = usuarioService.buscarPorUsername(authentication.getName());
		venta.setUsuario(user);
		///venta.setEstatus("Activa");

		List<Dosificacion> listaDosificacion = dosificacionService.buscarDosificacion();
		System.out.println(listaDosificacion.size());

		// adicionarndo venta
		ventaService.guardar(venta);

		// buscando ultima venta
		Venta vent = ventaService.buscarVenta();

		// guardando detalle venta
		String n = req.getParameter("numDetalles");
		DecimalFormat formato1 = new DecimalFormat("#.00");
		for (int i = 1; i <= Integer.parseInt(n); i++) {
			String cantidad = req.getParameter("cantidad" + i);
			String precio = req.getParameter("prec" + i);
			String subtotal = req.getParameter("subt" + i);
			String idDescuento = req.getParameter("desc" + i);
			String idHorario = req.getParameter("horario" + i);
			System.out.println("datos: " + cantidad + " " + precio + " " + subtotal + " " + idDescuento + " "
					+ idHorario + " " + vent.getId());
			detalleVentaService.guardar(Integer.parseInt(cantidad), Double.parseDouble(precio),
					Double.parseDouble(subtotal), Integer.parseInt(idDescuento), Integer.parseInt(idHorario),
					vent.getId());
			Horario horario = horarioService.buscarPorId(Integer.parseInt(idHorario));
			horario.setAsientosDisponibles(horario.getAsientosDisponibles()-Integer.parseInt(cantidad));
			horarioService.guardar(horario);
		}

		// obteniendo ultima factura

		Factura fac = new Factura();
		try {
			fac = ventaService.buscarFactura(listaDosificacion.get(0).getId());
		} catch (Exception e) {
			// Caso cuando es la primera factura de la dosificacion
			facturaService.guardar(listaDosificacion.get(0).getId(), vent.getId(), date,
					listaDosificacion.get(0).getnInicio(), infoCine.getId());

			// adicionando boletos
			List<DetalleVenta> listDetalles = detalleVentaService.listarPorId(vent.getId());
			for (int i = 0; i < listDetalles.size(); i++) {
				for (int j = 0; j < listDetalles.get(i).getCantidad(); j++) {
					ventaService.adicionarBoleto(date, hourFormat.format(date), listDetalles.get(i).getId(),
							listDetalles.get(i).getHorario().getId());

				}
			}
			fac = null;
		}

		if (fac != null) {
			// Caso cuando el numero fin de fatura ya se alcanzo
			if (fac.getNumero() == listaDosificacion.get(0).getnFin()) {
				detalleVentaService.borrarDetalles(vent.getId());
				ventaService.borrarFisico(vent.getId());
				flash.addFlashAttribute("error",
						"Error ya se inserto el ultimo numero de factura!, Insertar otra dosificacion");
				return "redirect:/ventas/listar";

			} else {
				// cuando hay alguna factura de esa dosificacion
				facturaService.guardar(listaDosificacion.get(0).getId(), vent.getId(), date, fac.getNumero() + 1,
						infoCine.getId());
				// adicionando boletos
				List<DetalleVenta> listDetalles = detalleVentaService.listarPorId(vent.getId());
				for (int i = 0; i < listDetalles.size(); i++) {
					for (int j = 0; j < listDetalles.get(i).getCantidad(); j++) {
						ventaService.adicionarBoleto(date, hourFormat.format(date), listDetalles.get(i).getId(),
								listDetalles.get(i).getHorario().getId());

					}
				}
			}
		}

		flash.addFlashAttribute("success", "Venta Guardada con éxito!");
		return "redirect:/ventas/listar";

	}

	@RequestMapping(value = "/detalleVenta/{id}")
	public String verDetalle(@PathVariable int id, Map<String, Object> model, RedirectAttributes flash) {

		model.put("titulo", "Detalle de Venta");
		model.put("venta", ventaService.buscarPorId(id));

		return "ventas/detalleVenta";
	}

	@RequestMapping(value = "/imprimirFactura/{id}", produces = "application/pdf")
	public String imprimirFactura(@PathVariable(value = "id") int id, Map<String, Object> model,
			RedirectAttributes flash) {
		System.out.println(id);
		Venta venta = ventaService.buscarPorId(id);
		Factura factura = facturaService.buscarFacturaPorVenta(venta.getId());
		System.out.println(factura.toString());

		double num = venta.getTotal();
		int nEntero = (int) (num);
		double decimal = num - nEntero;

		int num2 = (int) (decimal * 100);

		n2t numero = new n2t(num2);
		String res = numero.convertirLetras(nEntero);

		model.put("venta", venta);
		model.put("factura", factura);
		model.put("numero", res + " " + num2 + "/100");

		return "ventas/detalleVenta";

	}

	@RequestMapping(value = "/imprimirBoletos/{id}", produces = "application/pdf")
	public String imprimirBoletos(@PathVariable(value = "id") int id, Map<String, Object> model,
			Authentication authentication,	RedirectAttributes flash) {
		
		Usuario user = usuarioService.buscarPorUsername(authentication.getName());

		System.out.println(id);

		List<Boleto> lista = boletoService.listarPorId(id);
		model.put("lista", lista);
		model.put("user", user);

		return "ventas/imprimirBoletos";
	}
	
	@RequestMapping (value="/anular/{id}")
	public String anular(@PathVariable(value="id") int id) {
		
		System.out.println(id);
		if (id>0) {
		
			ventaService.anular(id);
		}	
		System.out.println("salio de la condicion");
		return "redirect:/ventas/listar";
	}
	
	@RequestMapping(value = "/mostrarDetalle", method = RequestMethod.GET)
	public String mostrarPelicula( @RequestParam("idHorario") int idHorario, Map<String, Object> model) throws ParseException {
		
		model.put("horario", horarioService.buscarPorId(idHorario));
	    return "ventas/mostrarDetalle :: resultsList";
	}
	
	@RequestMapping(value = "/buscarHorario/{term}", produces = { "application/json" })
	public @ResponseBody Horario buscaHorario(@PathVariable String term) {
		Horario horario = horarioService.buscarPorId(Integer.parseInt(term));
		
		return horario;
	}


}
