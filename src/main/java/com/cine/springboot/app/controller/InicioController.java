package com.cine.springboot.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cine.springboot.app.Utils.Utileria;
import com.cine.springboot.app.model.entity.Descuento;
import com.cine.springboot.app.model.entity.Horario;
import com.cine.springboot.app.model.entity.InformacionCine;
import com.cine.springboot.app.model.entity.Pelicula;
import com.cine.springboot.app.model.entity.Sala;
import com.cine.springboot.app.model.service.IBannersService;
import com.cine.springboot.app.model.service.IDescuentoService;
import com.cine.springboot.app.model.service.IHorarioService;
import com.cine.springboot.app.model.service.INoticiaService;
import com.cine.springboot.app.model.service.IPeliculaService;
import com.cine.springboot.app.model.service.InformacionService;








@Controller

public class InicioController {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@Autowired
	private IPeliculaService peliculaService;
	
	@Autowired
	private IHorarioService horarioService;
	
	@Autowired
	private InformacionService infoService;
	
	@Autowired
	private IDescuentoService descuentoService;
	
	@Autowired
	private IBannersService bannerService;
	
	@Autowired
	private INoticiaService noticiaService;
	


	
	@GetMapping("/")
	public String inicio(Model model) {
		try {
			Date fechaSinHora = dateFormat.parse(dateFormat.format(new Date()));
			List<String> listaFechas = Utileria.getNextDays(4);	
			List<Pelicula> peliculas = peliculaService.listarPorFecha(fechaSinHora);			
			model.addAttribute("fechas", listaFechas);
			model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
			model.addAttribute("peliculas", peliculas);
			model.addAttribute("publicidad", bannerService.listarActivos());
			model.addAttribute("noticias", noticiaService.listarActivas());		
			
		} catch (ParseException e) {
			System.out.println("Error: HomeController.mostrarPrincipal" + e.getMessage());
		}
		return "index";
		
	}
	@PostMapping("/buscar")
	public String buscar(@RequestParam("fecha") String fecha, Model model) throws ParseException {		

			System.out.println("FECHA INTROODUCIDA "+fecha);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date fecha1 = sdf.parse(fecha);
		
			System.out.println("asd" +fecha1);
			List<String> listaFechas = Utileria.getNextDays(4);
			List<Pelicula> peliculas  = peliculaService.listarPorFecha(fecha1);
			model.addAttribute("fechas", listaFechas);			
			
			model.addAttribute("fechaBusqueda",fecha);			
			model.addAttribute("peliculas", peliculas);
			model.addAttribute("publicidad", bannerService.listarActivos());
			model.addAttribute("noticias", noticiaService.listarActivas());
			for (int i = 0; i < peliculas.size(); i++) {
				System.out.println(peliculas.get(i));
			}
			
			return "index";
	
	}

	@RequestMapping(value="/informacion")
	public String informacion(Map<String, Object> model) {
		List<InformacionCine> info= infoService.listar();
		if (info.size()>0) {
			InformacionCine infoCine=infoService.buscarPorId(info.get(0).getId());	
			model.put("titulo", "Informacion del Cine");
			model.put("cine", infoCine);
			return "formInfo";
		}else {
			InformacionCine infoCine = new InformacionCine ();
			model.put("cine", infoCine);
			return "formInfo";
		}
		
	}
	@PostMapping("/guardar")
	public String guardar(InformacionCine cine,@RequestParam("file") MultipartFile foto) {
		System.out.println("esta es la foto"+ foto.getOriginalFilename());
		if(!foto.isEmpty()) {
			System.out.println("dentro if");
			Path directorioRecursos = Paths.get("src//main//resources//static//uploads");
			String rootPath = directorioRecursos.toFile().getAbsolutePath();
			try {
				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				cine.setLogo(foto.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		infoService.guardar(cine);
		return "redirect:/";
	}
	
	@GetMapping(value = "/consultarHorarios/{id}/{fecha}")
	public String consultarHorarios(@PathVariable("id") int idPelicula, @PathVariable("fecha") String fecha, Model model) throws ParseException {
		// TODO - Buscar en la base de datos los horarios.	
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha1 = sdf.parse(fecha);
		List<Horario> horarios= horarioService.listarPorPeliculaFecha(fecha1,idPelicula);
		model.addAttribute("horarios", horarios);
		Pelicula pelicula=peliculaService.buscarPorid(idPelicula);
		model.addAttribute("fechaBusqueda", fecha);
		model.addAttribute("pelicula",pelicula );	
		String string = pelicula.getDetalle().getTrailer();
		String[] parts = string.split("=");
		System.out.println(parts[1]);
		String part1 = parts[0]; // 123
		String part2 = parts[1]; // 654321
		model.addAttribute("trailer",part2 );	
		return "detalle";
	}
	
	@GetMapping("/proximamente")
	public String proximamente(Model model) throws ParseException {
		Date fechaSinHora = dateFormat.parse(dateFormat.format(new Date()));
	//	List<String> listaFechas = Utileria.getNextDays(4);	
	
		System.out.println(fechaSinHora);
		List<Pelicula> peliculas = peliculaService.listarPorFechaEstreno(fechaSinHora);			
	//	model.addAttribute("fechas", listaFechas);
	//	model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
		model.addAttribute("peliculas", peliculas);
		return "proximamente";
	}
	
	@GetMapping(value = "/consultarDetalle/{id}")
	public String cosultarDetalle(@PathVariable("id") int idPelicula, Model model) throws ParseException {

		Pelicula pelicula=peliculaService.buscarPorid(idPelicula);
	//	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	//	String fecha = sdf.format(pelicula.getFechaEstreno());
	//	model.addAttribute("fechaBusqueda", fecha);
		model.addAttribute("pelicula",pelicula );	
		String string = pelicula.getDetalle().getTrailer();
		String[] parts = string.split("=");
		String part2 = parts[1]; 
		model.addAttribute("trailer",part2 );	
		return "detalleProximamente";
	}
	
	@GetMapping("/promociones")
	public String promociones(Model model) throws ParseException {
		
	
		
		List<Descuento> descuentos = descuentoService.listarActivos();			
	//	model.addAttribute("fechas", listaFechas);
	//	model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
		model.addAttribute("descuentos", descuentos);
		return "promociones";
	}
	
	@GetMapping("/listarNoticias")
	public String listarNoticias(Model model) throws ParseException {
		model.addAttribute("publicidad", bannerService.listarActivos());
		model.addAttribute("noticias", noticiaService.listarActivas());
		return "noticias";
	}
	
	


}
