package com.warspringbootjava.WarSpringJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/index")
    public String index() {
        return "index";
    }
 
    
    @GetMapping("/listado-vehiculos")
    public String listadoVehiculos(Model model) {
		return "listado-vehiculos";
    }
    

    @GetMapping("/listado-guerreros")
    public String listadoGuerreros(Model model) {
		return "listado-guerreros";
    }
}


	