package com.cine.springboot.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cine.springboot.app.model.entity.Banner;
import com.cine.springboot.app.model.entity.Noticia;
import com.cine.springboot.app.model.entity.Usuario;
import com.cine.springboot.app.model.service.IBannersService;
import com.cine.springboot.app.model.service.INoticiaService;
import com.cine.springboot.app.model.service.IUsuarioService;

@Controller
@RequestMapping(value = "/noticia")
public class NoticiasController {
	
	@Autowired()
	private INoticiaService noticiaService;
	
	@Autowired
	private IUsuarioService userService;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		List<Noticia> lista = noticiaService.listar();  

		model.addAttribute("titulo", "Listado de Noticias");
		model.addAttribute("noticias", lista);

		return "noticia/lista";

	}
	
	@RequestMapping(value="/form")
	public String adicionar(Map<String, Object> model) {
		Noticia noticia = new Noticia();
		model.put("titulo", "Adicionar Noticia");
		model.put("noticia",noticia);
		return "noticia/formNoticia";
	}
	
	@PostMapping("/save")
	public String guardar(Noticia noticia, RedirectAttributes flash,Authentication authentication) {
		
		Date date = new Date();
		noticia.setFecha(date);
		
		Usuario user = userService.buscarPorUsername(authentication.getName());
		noticia.setUsuario(user);
		
		noticiaService.guardar(noticia);
	
		flash.addFlashAttribute("success", "Noticia guardada con éxito!");
		return "redirect:/noticia/listar";
	}
	
	@RequestMapping(value="/form/{id}")
	public String modificar(@PathVariable(value="id") int id,Map<String, Object> model) {
		Noticia noticia= null;
		if (id>0) {
			noticia = noticiaService.buscarPorId(id);
		}else {
			return "redirect:/listar";
		}
		model.put("titulo", "Modificar Noticia");
		model.put("noticia",noticia);
		return "noticia/formNoticia";
	}
	
	@RequestMapping (value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") int id, RedirectAttributes flash) {
		if (id>0) {
			
			noticiaService.borrar(id);
		}	
		
		flash.addFlashAttribute("success", "Noticia eliminada con éxito!");
		return "redirect:/noticia/listar";
	}

}
