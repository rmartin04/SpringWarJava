	package com.warspringbootjava.WarSpringJava.controller;
	
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.warspringbootjava.WarSpringJava.beans.LoginBean;
import com.warspringbootjava.WarSpringJava.beans.UsuarioBean;
import com.warspringbootjava.WarSpringJava.excepciones.UsuarioExisteException;
import com.warspringbootjava.WarSpringJava.excepciones.UsuarioNoEncontradoException;
import com.warspringbootjava.WarSpringJava.service.UsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
	
	@Controller
	public class UsuarioController {
		
		
		@Autowired
		UsuarioService usuarioService;
		
		// 1. Genera siempre los beans de formulario
	    @ModelAttribute("loginBean")
	    public LoginBean loginBean() {
	        return new LoginBean();
	    }

	    @ModelAttribute("usuarioBean")
	    public UsuarioBean usuarioBean() {
	        return new UsuarioBean();
	    }
	    
	    @GetMapping({ "/", "/login" })
	    public String mostrarLogin() {
	        return "login";   // templates/login.html
	    }
		
		@PostMapping("/registrarUsuario")
		public ModelAndView regitrarUsuario
		(
			@Valid @ModelAttribute("usuarioBean") UsuarioBean usuarioBean,
			BindingResult usuarioBindingResult
		) 
		{
			ModelAndView mav = new ModelAndView("login"); 
			mav.addObject("loginBean", new LoginBean());
			
			if (usuarioBindingResult.hasErrors()) {
		        mav.addObject("error", "No se pudo crear la cuenta. Por favor, corrige los errores.");
		        return mav;
		    }
			
			try {
				usuarioService.registrarUsuario(usuarioBean);
				mav.addObject("success", "Cuenta creada exitosamente");
				mav.addObject("usuarioBean", new UsuarioBean()); 
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
			
			if (loginBindingResult.hasErrors()) {
		        mav.addObject("error", "No se pudo iniciar sesión. Por favor, corrige los errores.");
		        return mav;
			}
			
			try {
				UsuarioBean usuario = usuarioService.validarUsuario(loginBean.getUsuario(), loginBean.getContrasenia());	
				session.setAttribute("usuarioLogueado", usuario);
				mav.addObject("loginBean", new LoginBean()); 
				return new ModelAndView("redirect:/index");
			} catch (UsuarioNoEncontradoException e) {
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
