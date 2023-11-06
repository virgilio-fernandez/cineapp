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

import com.cine.springboot.app.model.entity.Descuento;

import com.cine.springboot.app.model.service.IDescuentoService;

@Controller
@RequestMapping(value="/descuentos")
public class DescuentosController {
	

	
	@Autowired
	private IDescuentoService descuentoService;
	

	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("titulo","Listado de Descuentos");
		model.addAttribute("descuentos",descuentoService.listar());
		return "descuentos/listDescuentos";
		
	}
	
	@RequestMapping(value="/form")
	public String adicionar(Map<String, Object> model) {
		Descuento descuento = new Descuento();
		model.put("titulo", "Adicionar de Descuento");
		model.put("descuento",descuento);
		return "descuentos/formDescuento";
	}
	

	
	@GetMapping("/form/{id}")
	public String modificar(@PathVariable(value="id") int id,Map<String, Object> model) {
		Descuento descuento = null;

		if (id>0) {
			descuento = descuentoService.buscarPorId(id);
			System.out.println(descuento);
		}else {
			return "redirect:/listar";
		}
		model.put("titulo", "Modificar Descuento");
		model.put("descuento",descuento);
		return "descuentos/formDescuento";
	}
	@PostMapping("/save")
	public String guardar(Descuento descuento, RedirectAttributes flash) {
		descuentoService.guardar(descuento);
	
		flash.addFlashAttribute("success", "Descuento guardado con éxito!");
		return "redirect:/descuentos/listar";
	}
	
	@RequestMapping (value="/eliminar/{id}")
	public String borrar(@PathVariable(value="id") int id, RedirectAttributes flash) {
		System.out.println("dentro de borrar");
		System.out.println(id);
		if (id>0) {
			System.out.println("entro a la condicion");
			descuentoService.borrarLogico(id);
		}	
		System.out.println("salio de la condicion");
		flash.addFlashAttribute("success", "Descuento eliminado con éxito!");
		return "redirect:/descuentos/listar";
	}


	

}
