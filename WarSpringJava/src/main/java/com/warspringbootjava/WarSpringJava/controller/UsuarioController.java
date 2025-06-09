	package com.warsrpingbootjava.WarSpringJava.controller;
	
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.warsrpingbootjava.WarSpringJava.beans.LoginBean;
import com.warsrpingbootjava.WarSpringJava.beans.UsuarioBean;
import com.warsrpingbootjava.WarSpringJava.excepciones.UsuarioExisteException;
import com.warsrpingbootjava.WarSpringJava.excepciones.UsuarioNoEncontradoException;
import com.warsrpingbootjava.WarSpringJava.service.UsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
	
	@Controller
	public class UsuarioController {
		
		@Autowired
		UsuarioService usuarioService;

		
		@PostMapping("/registrarUsuario")
		public ModelAndView regitrarUsuario
		(
			@Valid @ModelAttribute("usuarioBean") UsuarioBean usuarioBean,
			BindingResult usuarioBindingResult,
			HttpSession session, 
			Model model
		) 
		{
			ModelAndView mav = new ModelAndView("login");
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
		}
			
		@PostMapping("/login")
		public ModelAndView login
		(
			HttpSession session,
			@Valid @ModelAttribute("loginBean") LoginBean loginBean,
			BindingResult loginBindingResult
		) 
		{
			ModelAndView mav = new ModelAndView("login");
			mav.addObject("usuarioBean", new UsuarioBean());  
			mav.addObject("loginBean", new LoginBean());  
			
			if (loginBindingResult.hasErrors()) {
		        mav.addObject("error", "No se pudo iniciar sesión. Por favor, corrige los errores.");
		        return mav;
			}
			
			try {
				UsuarioBean usuario = usuarioService.validarUsuario(loginBean.getUsuario(), loginBean.getContrasenia());	
				session.setAttribute("usuarioLogueado", usuario);
				return new ModelAndView("index");
			} catch (UsuarioNoEncontradoException e) {
				System.out.println("Usuario o contraseña incorrectos");
				mav.addObject("error", e.getMessage());
				e.printStackTrace();
				return mav;
			}
		}
		
		@PostMapping("/logout")
		public String logout(HttpSession session) {
		    session.invalidate();
		    return "/login";
		}
	
	}
