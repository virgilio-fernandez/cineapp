package com.cine.springboot.app.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout", required = false) String logout, 
			Model model, Principal principal,RedirectAttributes flash) {
	//	System.out.println("dentro login"+" error: "+error+"      logout:"+logout);
	//	System.out.println("ingresando a la pagina");
		if(principal !=null) {
			flash.addFlashAttribute("info", "Ya ha iniciado sesion anteriormente");
			return "redirect:/";
		}
		if (error != null) {
			model.addAttribute("error"," Error en el Login: nombre de usuario o contrasena incorrecta");
		}
		if(logout != null) {
			model.addAttribute("success", "Ha cerrado sesión con éxito!");
			return "redirect:/";
		}
		
		return "login";
	}
}
