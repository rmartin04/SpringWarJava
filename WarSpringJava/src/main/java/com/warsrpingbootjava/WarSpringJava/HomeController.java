package com.warsrpingbootjava.WarSpringJava;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
	public class HomeController {
	/**
	 * * Este es el controlador que maneja la ruta raíz de la aplicación.
	 * @return
	 */

	    @GetMapping("/")
	    public String home() {
	        return "login"; // nombre del archivo HTML sin la extensión
	    }
	}


