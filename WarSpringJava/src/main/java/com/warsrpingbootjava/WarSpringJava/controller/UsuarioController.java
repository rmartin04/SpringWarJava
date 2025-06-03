package com.warsrpingbootjava.WarSpringJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {
	
	@PostMapping("/login")
	public ModelAndView login
	(
		@RequestParam(name = "crearCuenta", required = false) String crearCuenta, 
		@RequestParam(name = "iniciarSesion", required = false) String iniciarSesion
	) 
	{
		if ("crearCuenta".equals(crearCuenta)) {
			return new ModelAndView("crear-cuenta");
			
		} else if ("iniciarSesion".equals(iniciarSesion)) {
			return new ModelAndView("iniciar-sesion");
		} else {
			return new ModelAndView("error"); // Manejo de error si no se selecciona ninguna opción
		}
	}

}
