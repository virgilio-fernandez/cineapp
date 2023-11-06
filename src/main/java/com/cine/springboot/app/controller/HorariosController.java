package com.cine.springboot.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cine.springboot.app.model.entity.Horario;
import com.cine.springboot.app.model.entity.Sala;
import com.cine.springboot.app.model.entity.TipoPelicula;
import com.cine.springboot.app.model.service.IHorarioService;
import com.cine.springboot.app.model.service.ISalaService;
import com.cine.springboot.app.model.service.ITipoPeliculaService;

@Controller
@RequestMapping(value = "/horarios")
public class HorariosController {
	@Autowired
	private IHorarioService horarioService;
	@Autowired
	private ISalaService salasService;
	@Autowired
	private ITipoPeliculaService tipoPeliculaService;

	@GetMapping("/listar")
	public String listar(Horario horario, Map<String, Object> model,HttpServletRequest request) {
		
		
		
		HttpSession misession= (HttpSession) request.getSession();
		Sala sala= (Sala) misession.getAttribute("sala");
		Date fecha= (Date)  misession.getAttribute("fecha");
		if (sala!=null && fecha!=null) {
			model.put("titulo", "Listado de horarios");
			horario.setFecha(fecha);
			horario.setSala(sala);
			model.put("horario", horario);
			model.put("salas", salasService.listarActivas());
			
		}else {
			model.put("titulo", "Listado de horarios");
			Date date=new Date();
			horario.setFecha(date);
			model.put("salas", salasService.listarActivas());
			model.put("horario", horario);
		}
		
		
		return "horarios/listHorarios";

	}

	@RequestMapping(value = "/listarHorarios", method = RequestMethod.GET)
	public String filtrar(Map<String, Object> model, @RequestParam("fecha") String fecha,
			@RequestParam("sala") String sala) throws ParseException {
		System.out.println("fecha: " + fecha);
		System.out.println("sala: " + sala);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha1 = sdf.parse(fecha);

		model.put("horarios", horarioService.listarPorFechaSala(fecha1, Integer.parseInt(sala)));
		return "horarios/listar :: lista";
	}

	@RequestMapping(value = "/form")
	public String adicionar(Horario horario, Map<String, Object> model, @RequestParam("fecha") String fecha,
			@RequestParam("sala") String sala) throws ParseException {
		
		model.put("titulo", "Adicionar  Horario");

		model.put("peliculasTipos", tipoPeliculaService.listarPorPeliculaActiva());


	

		model.put("salas", salasService.listarActivas());
		model.put("h", horario);
		return "horarios/formHorario";
	}

	@PostMapping("/save")
	public String guardar(Horario horario, @RequestParam("pelicula") String pelicula, Map<String, Object> model, RedirectAttributes flash,HttpServletRequest request) {
		System.out.println(pelicula);
		TipoPelicula tipoPelicula = new TipoPelicula();
		String[] parts = pelicula.split(",");
		String part1 = parts[0]; 
		String part2 = parts[1];
		int idPelicula = Integer.parseInt(part1);
		int idTipo = Integer.parseInt(part2);
		tipoPelicula = tipoPeliculaService.buscarPorId(idPelicula, idTipo);
		horario.setPeliculaTipo(tipoPelicula);

		Sala sala = salasService.buscarPorId(horario.getSala().getId());
		horario.setAsientosDisponibles(sala.getnAsientos());

		horarioService.guardar(horario);
		flash.addFlashAttribute("success", "Horario guardado con éxito!");
		
		
		model.put("titulo", "Listado de horarios");
		
		model.put("salas", salasService.listarActivas());

		
		model.put("horario", horario);
	//	return "horarios/listHorarios";
		
		HttpSession misession= request.getSession(true);
		misession.setAttribute("fecha",horario.getFecha());
		misession.setAttribute("sala",sala);
		
		
		return "redirect:/horarios/listar";
	}

	@RequestMapping(value = "/form/{id}")
	public String modificar(@PathVariable(value = "id") int id, Map<String, Object> model) {
		Horario horario = null;

		if (id > 0) {
			horario = horarioService.buscarPorId(id);
		} else {
			return "redirect:/listar";
		}
		model.put("titulo", "Modificar Horario");
		model.put("horario", horario);

//		model.put("peliculas",peliculaService.findAll());
		model.put("peliculasTipos", tipoPeliculaService.findAll());
		model.put("IdTipo", horario.getPeliculaTipo().getTipo().getId());
		model.put("IdPelicula", horario.getPeliculaTipo().getPelicula().getId());
		model.put("salas", salasService.listarActivas());
		return "horarios/formHorario";
	}

	/*@RequestMapping(value = "/eliminar/{id}")
	public String borrar(@PathVariable(value = "id") int id) {
		System.out.println("dentro de borrar");
		System.out.println(id);
		if (id > 0) {
			System.out.println("entro a la condicion");
			horarioService.borrarLogico(id);
		}
		System.out.println("salio de la condicion");
		return "redirect:/horarios/listar";
	}
	*/
/*	@GetMapping(value = "/eliminar/{id}", produces = { "application/json" })
	public @ResponseBody Horario eliminar(@PathVariable int id) {
		
		horarioService.borrarLogico(id);
		Horario horario = horarioService.buscarPorId(id);
		
		return horario;
	}
*/	
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash,HttpServletRequest request) {	
		horarioService.borrarLogico(id);
		Horario horario = horarioService.buscarPorId(id);
		HttpSession misession= request.getSession(true);
		misession.setAttribute("fecha",horario.getFecha());
		misession.setAttribute("sala",horario.getSala());
		
		flash.addFlashAttribute("success", "Horario eliminado con éxito!");
		
		
		return "redirect:/horarios/listar";
	}
	
	
}
