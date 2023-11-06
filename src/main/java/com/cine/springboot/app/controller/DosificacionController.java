package com.cine.springboot.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cine.springboot.app.model.entity.Dosificacion;
import com.cine.springboot.app.model.service.IDosificacionService;


@Controller
@RequestMapping(value="/dosificacion")
public class DosificacionController {
	@Autowired
	private IDosificacionService dosificacionService;
	
	@GetMapping("/listar")
	public String listar(Model model) {
	
		model.addAttribute("titulo","Listado de tipos de Dosificaciones");
		model.addAttribute("dosificaciones",dosificacionService.findAll());
		return "dosificaciones/listDosificaciones";
		
	}
	
	@RequestMapping(value="/form")
	public String adicionar(Map<String, Object> model) {
		Dosificacion dosificacion = new Dosificacion();
		model.put("titulo", "Adicionar Dosificacion");
		model.put("dosificacion", dosificacion);
		return "dosificaciones/formDosificacion";
	}
	@RequestMapping(value="/form/{id}")
	public String modificar(@PathVariable(value="id") int id,Map<String, Object> model) {
		Dosificacion dosificacion =  null;
		if (id>0) {
			dosificacion = dosificacionService.findOne(id);
		}else {
			return "redirect:/dosificacion/listar";
		}
		model.put("titulo", "Modificar Dosificacion");
		model.put("dosificacion",dosificacion);
		return "dosificaciones/formDosificacion";
	}
	
	@PostMapping("/save")
	public String guardar(Dosificacion dosificacion, RedirectAttributes flash) {
		
		dosificacionService.save(dosificacion);
		flash.addFlashAttribute("success", "Dosificacion guardada con éxito!");
		return "redirect:/dosificacion/listar";
	}
	
}
