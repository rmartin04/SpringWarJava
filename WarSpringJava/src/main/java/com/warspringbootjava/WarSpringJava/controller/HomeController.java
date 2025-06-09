package com.warspringbootjava.WarSpringJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.warspringbootjava.WarSpringJava.beans.UsuarioBean;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("usuarioLogueado")
public class HomeController {

    // Inyecta el usuario logueado en todas las vistas internas
    @ModelAttribute("usuarioLogueado")
    public UsuarioBean usuarioEnSesion(HttpSession session) {
        return (UsuarioBean) session.getAttribute("usuarioLogueado");
    }
    
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/listado-guerreros")
    public String listadoGuerreros(Model model) {
		return "listado-guerreros";
    }
    
    @GetMapping("/listado-vehiculos")
    public String listadoVehiculos(Model model) {
		return "listado-vehiculos";
    }
    
    @GetMapping("guerreros/crear-guerrero")
    public String crearGuerrero(Model model) {
    	return "crear-guerrero";
    }
}


	