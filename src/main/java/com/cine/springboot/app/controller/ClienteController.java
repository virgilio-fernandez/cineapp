package com.cine.springboot.app.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cine.springboot.app.model.entity.Cliente;
import com.cine.springboot.app.model.service.IClienteService;


@Controller
@RequestMapping(value="/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/listar")
	public String listar(Model model) {
	
		model.addAttribute("titulo","Listado de Clientes");
		model.addAttribute("clientes",clienteService.listar());
		return "clientes/listClientes";
		
	}
	
	@RequestMapping(value="/form")
	public String adicionar(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("titulo", "Adicionar de Cliente");
		model.put("cliente",cliente);
		return "clientes/formCliente";
	}
	@RequestMapping(value="/form/{id}")
	public String modificar(@PathVariable(value="id") int id,Map<String, Object> model) {
		Cliente cliente =  null;
		if (id>0) {
			cliente = clienteService.buscarPorId(id);
		}else {
			return "redirect:/clientes/listar";
		}
		model.put("titulo", "Modificar Cliente");
		model.put("cliente",cliente);
		return "clientes/formCliente";
	}
	
	@PostMapping("/save")
	public String guardar(Cliente cliente, RedirectAttributes flash) {
		Date date = new Date();
		cliente.setFecha(date);
		clienteService.guardar(cliente);
		flash.addFlashAttribute("success", "Cliente guardado con éxito!");
		return "redirect:/clientes/listar";
	}
	
	@RequestMapping (value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") int id, RedirectAttributes flash) {
		
		System.out.println(id);
		if (id>0) {
			
			clienteService.eliminar(id);
		}	
		flash.addFlashAttribute("success", "Cliente eliminado con éxito!");
		
		return "redirect:/clientes/listar";
	}

}
