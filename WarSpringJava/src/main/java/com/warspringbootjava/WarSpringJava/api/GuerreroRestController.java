package com.warspringbootjava.WarSpringJava.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.warspringbootjava.WarSpringJava.entities.Guerrero;
import com.warspringbootjava.WarSpringJava.service.GuerreroService;

@RestController
@RequestMapping("/api/guerreros")
public class GuerreroRestController {

	    private final GuerreroService service;
	    public GuerreroRestController(GuerreroService service) {
	        this.service = service;
	    }

	    @GetMapping
	    public List<Guerrero> filtrar(
	        @RequestParam(required = false) String tipo,
	        @RequestParam(required = false) String search
	    ) {
	        return service.filtrarPorTipoYNombre(tipo, search);
	    
	}
	
}
