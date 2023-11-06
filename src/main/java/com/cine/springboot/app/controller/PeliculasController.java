package com.cine.springboot.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cine.springboot.app.model.entity.Pelicula;
import com.cine.springboot.app.model.entity.TipoPelicula;
import com.cine.springboot.app.model.service.IDetalleService;
import com.cine.springboot.app.model.service.IPeliculaService;
import com.cine.springboot.app.model.service.ITipoPeliculaService;
import com.cine.springboot.app.model.service.ITipoService;

//import net.itinajero.app.util.Utileria;

@Controller
@RequestMapping(value="/peliculas")
public class PeliculasController {
	
	

	
	@Autowired
	private IPeliculaService peliculaService;
	
	@Autowired
	private IDetalleService detalleService;
/*	@Autowired
	private ITipoPeliculaService tipoPeliculaService;
	
	@Autowired
	private ITipoService tipoService;
*/	
	
	
	@GetMapping("/listar")
	public String listar(Model model) {
		System.out.println("dentro servlet");
		model.addAttribute("titulo","Listado de peliculas");
		model.addAttribute("peliculas",peliculaService.listar());
		return "peliculas/listPeliculas";
		
	}
	
	@RequestMapping(value="/form")
	public String adicionar(Map<String, Object> model) {
		Pelicula pelicula = new Pelicula();
		model.put("titulo", "Adicionar Pelicula");
		model.put("pelicula",pelicula);
		return "peliculas/formPelicula";
	}
	

	
	@RequestMapping(value="/form/{id}")
	public String modificar(@PathVariable(value="id") int id,Map<String, Object> model) {
		Pelicula pelicula = null;
		if (id>0) {
			pelicula = peliculaService.buscarPorid(id);
		}else {
			return "redirect:/listar";
		}
		model.put("titulo", "Modificar Pelicula");
		model.put("pelicula",pelicula);
		return "peliculas/formPelicula";
	}
	@PostMapping("/save")
	public String guardar(Pelicula pelicula,@RequestParam("file") MultipartFile foto, RedirectAttributes flash) {
		System.out.println("esta es la foto"+ foto.getOriginalFilename());
		if(!foto.isEmpty()) {
			System.out.println("dentro if");
			String rootPath ="C://Temp//uploads//img-peliculas";
			try {
				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				pelicula.setImagen(foto.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
		peliculaService.guardar(pelicula);
		//detalleService.save(pelicula.getDetalle());
	
		flash.addFlashAttribute("success", "Pelicula guardada con éxito!");
		return "redirect:/peliculas/listar";
	}
	
	@RequestMapping (value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") int id, RedirectAttributes flash) {
		System.out.println("dentro de borrar");
		System.out.println(id);
		if (id>0) {
			System.out.println("entro a la condicion");
			peliculaService.eliminar(id);
		}	
		System.out.println("salio de la condicion");
		flash.addFlashAttribute("success", "Pelicula eliminada con éxito!");
		return "redirect:/peliculas/listar";
	}
	@RequestMapping (value="/mostrarTipos/{id}")
	public String mostrarTipos(@PathVariable(value="id") int id,Model model) {
		model.addAttribute("titulo","Listado de tipos");
	//	model.addAttribute("tiposAsignados",tipoPeliculaService.listarPorId(id));
		model.addAttribute("tiposAsignados",peliculaService.listarTiposAsig(id));
		model.addAttribute("tiposNoAsignados",peliculaService.listarTiposNoAsig(id));
		model.addAttribute("idPelicula",id);
		return "peliculas/mostrarTipos"; 
	}
	
	/*@RequestMapping (value="/asignarTipos/{id}")
	public String asignarTipos(@PathVariable(value="id") int id) {
		System.out.println("dentro de borrar");
		System.out.println(id);
		if (id>0) {
			System.out.println("entro a la condicion");
			peliculaService.delete(id);
		}	
		System.out.println("salio de la condicion");
		return "redirect:/peliculas/listar";  
	}
*/	 
	@RequestMapping (value="/quitar/{idPelicula}/{idTipo}")
	public String  quitarTipo(@PathVariable(value="idPelicula") int idPelicula,@PathVariable(value="idTipo") int idTipo, RedirectAttributes flash) {
		System.out.println("dentro de borrar");
		System.out.println(idTipo );
		if (idPelicula>0) {
			System.out.println(idPelicula+"  "+idTipo);
			try {
				peliculaService.quitarTipo(idPelicula,idTipo);
			} catch (Exception e) {
				flash.addFlashAttribute("error", "Ocurrio un problema no se puede eliminar!");
				return "redirect:/peliculas/mostrarTipos/"+idPelicula;
			}
			
		}	
		System.out.println("salio de la condicion");
		flash.addFlashAttribute("success", "Se elimino con éxito!");
		return "redirect:/peliculas/mostrarTipos/"+idPelicula;
	}
	
	@RequestMapping (value="/asignar/{idPelicula}/{idTipo}")
	public String  asignarTipo(@PathVariable(value="idPelicula") int idPelicula,@PathVariable(value="idTipo") int idTipo, RedirectAttributes flash) {
		System.out.println("dentro de asignar");
		System.out.println(idTipo );
		if (idPelicula>0) {
			System.out.println(idPelicula+"  "+idTipo);
			peliculaService.asignarTipo(idPelicula,idTipo);
		}	
		System.out.println("salio de la condicion");
		flash.addFlashAttribute("success", "Se asigno con éxito!");
		return "redirect:/peliculas/mostrarTipos/"+idPelicula; 
	}
	
	
}
