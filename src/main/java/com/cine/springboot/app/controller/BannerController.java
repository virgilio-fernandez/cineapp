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

import com.cine.springboot.app.model.entity.Usuario;

import com.cine.springboot.app.model.service.IBannersService;
import com.cine.springboot.app.model.service.IUsuarioService;

@Controller
@RequestMapping(value = "/banner")
public class BannerController {

	@Autowired()
	private IBannersService bannerService;
	
	@Autowired
	private IUsuarioService userService;

	@GetMapping("/listar")
	public String listar(Model model) {
		System.out.println("dentro servlet");

		List<Banner> lista = bannerService.listar();

		model.addAttribute("titulo", "Listado de Imagenes");
		model.addAttribute("banner", lista);

		return "banner/lista";

	}
	
	@RequestMapping(value="/form")
	public String adicionar(Map<String, Object> model) {
		Banner banner = new Banner();
		model.put("titulo", "Adicionar Imagen");
		model.put("banner",banner);
		return "banner/formBanner";
	}
	
	@PostMapping("/save")
	public String guardar(Banner banner,@RequestParam("file") MultipartFile foto, RedirectAttributes flash,	Authentication authentication) {
		System.out.println("esta es la foto"+ foto.getOriginalFilename());
		if(!foto.isEmpty()) {
			String rootPath ="C://Temp//uploads//img-banner";
			try {
				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				banner.setArchivo(foto.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Date date = new Date();
		banner.setFecha(date);
		
		Usuario user = userService.buscarPorUsername(authentication.getName());
		banner.setUsuario(user);
		
		bannerService.guardar(banner);
	
	
		
		flash.addFlashAttribute("success", "Imagen guardada con éxito!");
		return "redirect:/banner/listar";
	}
	
	@RequestMapping(value="/form/{id}")
	public String modificar(@PathVariable(value="id") int id,Map<String, Object> model) {
		Banner banner= null;
		if (id>0) {
			banner = bannerService.buscarPorId(id);
		}else {
			return "redirect:/listar";
		}
		model.put("titulo", "Modificar Imagen");
		model.put("banner",banner);
		return "banner/formBanner";
	}
	
	@RequestMapping (value="/eliminar/{id}")
	public String borrar(@PathVariable(value="id") int id, RedirectAttributes flash) {
		
		System.out.println(id);
		if (id>0) {
			
			bannerService.eliminar(id);
		}	
		
		flash.addFlashAttribute("success", "Imagen eliminada con éxito!");
		return "redirect:/banner/listar";
	}
	

}
