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

import com.cine.springboot.app.model.entity.Sala;
import com.cine.springboot.app.model.service.ISalaService;

@Controller
@RequestMapping(value="/salas")
public class SalasController {
	
	@Autowired
	private ISalaService salasService;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de salas");
		model.addAttribute("salas",salasService.listar());
		return "salas/listSalas";
		
	}
	
	@RequestMapping(value="/form")
	public String adicionar(Map<String, Object> model) {
		Sala sala = new Sala();
		model.put("titulo", "Adicionar Sala");
		model.put("sala",sala);
		return "salas/formSala";
	}
	
	@RequestMapping(value="/form/{id}")
	public String modificar(@PathVariable(value="id") int id,Map<String, Object> model) {
		Sala sala = null;
		if (id>0) {
			sala = salasService.buscarPorId(id);
		}else {
			return "redirect:/salas/listar";
		}
		model.put("titulo", "Modificar Sala");
		model.put("sala",sala);
		return "salas/formSala";
	}
	
	@PostMapping("/save")
	public String guardar(Sala sala, RedirectAttributes flash) {
		salasService.guardar(sala);
		flash.addFlashAttribute("success", "Sala guardada con éxito!");
		return "redirect:/salas/listar";
	}
	
	@RequestMapping (value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") int id, RedirectAttributes flash) {
		if (id>0) {
			salasService.borrarLogico(id);
			flash.addFlashAttribute("success", "Sala eliminada con éxito!");
		}	
		
		return "redirect:/salas/listar";
	}

}
