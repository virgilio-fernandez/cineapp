package com.cine.springboot.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.cine.springboot.app.model.entity.Rol;
import com.cine.springboot.app.model.entity.Usuario;

import com.cine.springboot.app.model.service.IUsuarioService;


@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private IUsuarioService usuarioService;
	


	@GetMapping("/listar")
	public String listar(Model model) {

		model.addAttribute("titulo", "Listado de Usuarios");
		model.addAttribute("usuarios", usuarioService.listar());
		return "usuarios/listUsuarios";

	}

	@RequestMapping(value = "/form")
	public String adicionar(Map<String, Object> model) {
		Usuario usuario = new Usuario();
		model.put("titulo", "Adicionar Usuario");
		model.put("usuario", usuario);
		return "usuarios/formUsuario";
	}
	

	@RequestMapping(value = "/form/{id}")
	public String modificar(@PathVariable(value = "id") int id, Map<String, Object> model) {
		Usuario usuario = null;
		if (id > 0) {
			usuario = usuarioService.buscarPorId(id);
		} else {
			return "redirect:/usuarios/listar";
		}
		model.put("titulo", "Modificar Usuario");
		model.put("usuario", usuario);
	
		
		List<Rol> lista = new ArrayList<Rol>();
		lista=usuario.getRoles();
		System.out.println(lista.size());
		if (lista.size()==2) {
			model.put("rol1", lista.get(0).getRol());
			model.put("rol2", lista.get(1).getRol());
		
		}else {
			model.put("rol1", lista.get(0).getRol());
			model.put("rol2", null);
		}
		return "usuarios/formUsuario";
	}
	

	@Transactional
	@PostMapping("/save")
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model,
			@RequestParam("roles") String roles, RedirectAttributes flash) {
		System.out.println("dentro de guardar usuario");

		
		if (usuario.getId()>0) {
			Usuario usuario2 = usuarioService.buscarPorId(usuario.getId());
		
				
			System.out.println("para modificar");
			List<Rol> listarol=usuario2.getRoles();
			System.out.println(listarol.size());
			for (int i = 0; i < listarol.size(); i++) {
				System.out.println(listarol.get(i).getId());
				usuarioService.eliminarRol(listarol.get(i).getId()); 
					
		
			}
			
		}
	
		
		List<Rol> lista = new ArrayList<Rol>();
		if (roles != null) {
			String[] parts = roles.split(",");
			if (parts.length == 2) {
				
				Rol rol = new Rol();
				Rol rol2 = new Rol();
				rol.setRol(parts[0]);
				rol2.setRol(parts[1]);
				lista.add(rol);
				lista.add(rol2);
			} else {
				Rol rol = new Rol();
				
				rol.setRol(parts[0]);
				lista.add(rol);
			}
		}
	
		usuario.setRoles(lista);
		String bcryptPassword = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(bcryptPassword);
		
		try {
			usuarioService.guardar(usuario);
			
		} catch (Exception e) {
			flash.addFlashAttribute("danger", "Ocurrio un error login ya existente!");
		}
		flash.addFlashAttribute("success", "Usuario  guardado con éxito!");
		
	
		return "redirect:/usuarios/listar";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") int id, RedirectAttributes flash) {

		System.out.println(id);
		if (id > 0) {

			usuarioService.eliminar(id);
			flash.addFlashAttribute("success", "Usuario eliminado con éxito!");
		}

		return "redirect:/usuarios/listar";
	}

}
