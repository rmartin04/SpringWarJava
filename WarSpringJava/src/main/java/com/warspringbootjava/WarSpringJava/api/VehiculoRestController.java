package com.warspringbootjava.WarSpringJava.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.warspringbootjava.WarSpringJava.entities.VehiculoGuerra;
import com.warspringbootjava.WarSpringJava.service.VehiculoService;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoRestController {
	
	private final VehiculoService service;
    public VehiculoRestController(VehiculoService service) {
        this.service = service;
    }

    @GetMapping
    public List<VehiculoGuerra> filtrar(
        @RequestParam(required = false) String tipo,
        @RequestParam(required = false) String search
    ) {
        return service.filtrar(tipo, search);
    
    }

}
