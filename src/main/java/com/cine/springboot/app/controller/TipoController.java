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

import com.cine.springboot.app.model.entity.Tipo;
import com.cine.springboot.app.model.service.ITipoService;

@Controller
@RequestMapping(value="/tipos")
public class TipoController {
	@Autowired
	private ITipoService tipoService;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de tipos de peliculas");
		model.addAttribute("tipos",tipoService.listar());
		return "tipos/listTipos";
		
	}
	
	@RequestMapping(value="/form")
	public String adicionar(Map<String, Object> model) {
		Tipo tipo1 = new Tipo();
		model.put("titulo", "Adicionar tipo de pelicula");
		model.put("tipo1",tipo1);
		return "tipos/formTipo";
	}
	@RequestMapping(value="/form/{id}")
	public String modificar(@PathVariable(value="id") int id,Map<String, Object> model) {
		Tipo tipo1 =  null;
		if (id>0) {
			tipo1 = tipoService.buscarPorId(id);
		}else {
			return "redirect:/tipos/listar";
		}
		model.put("titulo", "Modificar tipo de pelicula");
		model.put("tipo1",tipo1);
		return "tipos/formTipo";
	}
	
	@PostMapping("/save")
	public String guardar(Tipo tipo, RedirectAttributes flash) {
		tipoService.guardar(tipo);
		flash.addFlashAttribute("success", "Tipo  guardado con éxito!");
		return "redirect:/tipos/listar";
	}
	
	@RequestMapping (value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") int id, RedirectAttributes flash) {
		if (id>0) {
			tipoService.borrarLogico(id);
			flash.addFlashAttribute("success", "Tipo eliminado con éxito!");
		}
		return "redirect:/tipos/listar";
	}
}
