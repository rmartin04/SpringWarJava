package com.warsrpingbootjava.WarSpringJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.warsrpingbootjava.WarSpringJava.beans.UsuarioBean;
import com.warsrpingbootjava.WarSpringJava.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("/login")
	public ModelAndView login
	(
		@Valid @ModelAttribute("usuarioBean") UsuarioBean usuarioBean,
		BindingResult bindingResult,
		@RequestParam(name = "crearCuenta", required = false) String crearCuenta, 
		@RequestParam(name = "iniciarSesion", required = false) String iniciarSesion,
		Model model
	) 
	{
		if ("crearCuenta".equals(crearCuenta)) {
			
			if (bindingResult.hasErrors()) {
		        ModelAndView mav = new ModelAndView("login");
		        mav.addObject("usuarioBean", usuarioBean);
		        return mav;
		    }
			
			ModelAndView mav = new ModelAndView("login");
			mav.addObject("mensaje", "Cuenta creada exitosamente");
			mav.addObject("usuarioBean", new UsuarioBean()); // resetea el formulario si quieres
			return mav;
			
		} else if ("iniciarSesion".equals(iniciarSesion)) {
			return new ModelAndView("iniciar-sesion");
		} else {
			return new ModelAndView("error"); // Manejo de error si no se selecciona ninguna opción
		}
	}

}
