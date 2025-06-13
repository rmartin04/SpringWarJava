package com.warspringbootjava.WarSpringJava.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.warspringbootjava.WarSpringJava.beans.BatallaResultadoDTO;
import com.warspringbootjava.WarSpringJava.entities.VehiculoGuerra;
import com.warspringbootjava.WarSpringJava.service.BatallaService;
import com.warspringbootjava.WarSpringJava.service.VehiculoService;

@Controller
@RequestMapping("/batalla")
public class BatallaController {

    private final VehiculoService vehiculoService;
    private final BatallaService batallaService;

    public BatallaController(VehiculoService vehiculoService, BatallaService batallaService) {
        this.vehiculoService = vehiculoService;
        this.batallaService = batallaService;
    }

    @GetMapping
    public String formularioBatalla(Model model) {
        List<VehiculoGuerra> vehiculos = vehiculoService.listarVehiculos();
        model.addAttribute("vehiculos", vehiculos);
        return "batalla-formulario";
    }

    @PostMapping("/iniciar")
    public String iniciarBatalla(
            @RequestParam Long vehiculo1Id,
            @RequestParam Long vehiculo2Id,
            Model model) {
    	
        if (vehiculo1Id.equals(vehiculo2Id)) {
            model.addAttribute("error", "No puedes enfrentar un vehículo contra sí mismo.");
            model.addAttribute("vehiculos", vehiculoService.listarVehiculos());
            return "batalla-formulario";
        }
        
        try {
            BatallaResultadoDTO resultado = batallaService.iniciarBatallaPorTurnos(vehiculo1Id, vehiculo2Id);
            model.addAttribute("resultado", resultado);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        List<VehiculoGuerra> vehiculos = vehiculoService.listarVehiculos();
        model.addAttribute("vehiculos", vehiculos);
        return "batalla-formulario";
    }
}