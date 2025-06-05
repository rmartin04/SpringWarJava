	package com.warsrpingbootjava.WarSpringJava.controller;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.validation.BindingResult;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.servlet.ModelAndView;
	
	import com.warsrpingbootjava.WarSpringJava.beans.LoginBean;
	import com.warsrpingbootjava.WarSpringJava.beans.UsuarioBean;
	import com.warsrpingbootjava.WarSpringJava.excepciones.UsuarioExisteException;
import com.warsrpingbootjava.WarSpringJava.excepciones.UsuarioNoEncontradoException;
import com.warsrpingbootjava.WarSpringJava.service.UsuarioService;
	
	import jakarta.validation.Valid;
	
	@Controller
	public class UsuarioController {
		
		@Autowired
		UsuarioService usuarioService;
		
		@PostMapping("/login")
		public ModelAndView login
		(
			@Valid @ModelAttribute("loginBean") LoginBean loginBean,
			BindingResult loginBindingResult,
			@Valid @ModelAttribute("usuarioBean") UsuarioBean usuarioBean,
			BindingResult usuarioBindingResult,
			@RequestParam(name = "crearCuenta", required = false) String crearCuenta, 
			@RequestParam(name = "iniciarSesion", required = false) String iniciarSesion,
			Model model
		) 
		{
			ModelAndView mav = new ModelAndView("login");
			if ("crearCuenta".equals(crearCuenta)) {
				
				if (usuarioBindingResult.hasErrors()) {
			        mav.addObject("error", "No se pudo crear la cuenta. Por favor, corrige los errores.");
			        return mav;
			    }
				
				try {
					mav.addObject("logout", "Cuenta creada exitosamente");
					mav.addObject("usuarioBean", new UsuarioBean()); // resetea el formulario si quieres
					usuarioService.registrarUsuario(usuarioBean);
				} catch (UsuarioExisteException e) {
					mav.addObject("error", e.getMessage());
					e.printStackTrace();
				}
				return mav;
				
			} else if ("iniciarSesion".equals(iniciarSesion)) {
				if (loginBindingResult.hasErrors()) {
			        mav.addObject("error", "No se pudo iniciar sesión. Por favor, corrige los errores.");
			        return mav;
				}
				
				try {
					usuarioService.validarUsuario(loginBean.getUsuario(), loginBean.getContrasenia());	
					return new ModelAndView("index");
				} catch (UsuarioNoEncontradoException e) {
					mav.addObject("error", e.getMessage());
					e.printStackTrace();
					return mav;
				}
				
			} else {
				return new ModelAndView("error"); // Manejo de error si no se selecciona ninguna opción
			}
		}
	
	}
