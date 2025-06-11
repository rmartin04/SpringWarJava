package com.warspringbootjava.WarSpringJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.warspringbootjava.WarSpringJava.beans.LoginBean;
import com.warspringbootjava.WarSpringJava.beans.UsuarioBean;
import com.warspringbootjava.WarSpringJava.excepciones.UsuarioExisteException;
import com.warspringbootjava.WarSpringJava.excepciones.UsuarioNoEncontradoException;
import com.warspringbootjava.WarSpringJava.service.UsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UsuarioController {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UsuarioController.class);

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

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
		return "login"; // templates/login.html
	}

	@PostMapping("/registrarUsuario")
	public String registrar(@Valid @ModelAttribute("usuarioBean") UsuarioBean bean, BindingResult rs, Model m) {
		if (rs.hasErrors()) {
			m.addAttribute("error", "Corrige los errores.");
			logger.error("Errores en el formulario de registro: {}", rs.getAllErrors());
			return "login";
		}
		try {
			usuarioService.registrarUsuario(bean);
			m.addAttribute("success", "Cuenta creada.");
			logger.info("Usuario registrado exitosamente: {}", bean.getUsuario());
		} catch (UsuarioExisteException e) {
			m.addAttribute("error", e.getMessage());
			logger.error("Error al registrar usuario: {}", e.getMessage());
		}
		return "login";
	}

	@PostMapping("/login")
	public String login(HttpSession session, @Valid @ModelAttribute("loginBean") LoginBean bean, BindingResult rs,
			Model m) {
		if (rs.hasErrors()) {
			m.addAttribute("error", "Corrige los errores.");
			logger.error("Errores en el formulario de login: {}", rs.getAllErrors());
			return "login";
		}
		try {
			UsuarioBean u = usuarioService.validarUsuario(bean.getUsuario(), bean.getContrasenia());
			session.setAttribute("usuarioLogueado", u);
			logger.info("Usuario logueado exitosamente: {}", u.getUsuario());
			return "redirect:/index";
		} catch (UsuarioNoEncontradoException e) {
			m.addAttribute("error", e.getMessage());
			logger.error("Error al iniciar sesión: {}", e.getMessage());
			return "login";
		}
	}

	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

}
