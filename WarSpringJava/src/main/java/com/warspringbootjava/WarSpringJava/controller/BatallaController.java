package com.warspringbootjava.WarSpringJava.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.warspringbootjava.WarSpringJava.entities.VehiculoGuerra;
import com.warspringbootjava.WarSpringJava.repositories.VehiculosGuerraRepository;
import com.warspringbootjava.WarSpringJava.service.BatallaService;

@Controller
@RequestMapping("/batalla")
public class BatallaController {
    private static final Logger logger = LoggerFactory.getLogger(BatallaController.class);

    @Autowired
    private BatallaService batallaService;

    @Autowired
    private VehiculosGuerraRepository vehiculosGuerraRepository;

    // Mostrar la página con la lista de vehículos y el formulario
    @GetMapping
    public String mostrarPagina(Model model) {
        List<VehiculoGuerra> vehiculos = vehiculosGuerraRepository.findAll();

        // Construir los strings con nombres de guerreros para cada vehículo
        vehiculos.forEach(vehiculo -> {
            String guerrerosNombres = vehiculo.getGuerreros().stream()
                .map(g -> g.getNombre())
                .collect(Collectors.joining(", "));
            //vehiculo.setGuerrerosNombres(guerrerosNombres); // nuevo atributo que debes agregar en VehiculosGuerra
        });

        model.addAttribute("vehiculos", vehiculos);
        return "batalla"; // nombre de la plantilla HTML sin extensión
    }

    // Manejar la petición para iniciar la batalla
    @GetMapping("/iniciar")
    public String empezarBatalla(
            @RequestParam Long idVehiculo1,
            @RequestParam Long idVehiculo2,
            Model model) {

        List<VehiculoGuerra> vehiculos = vehiculosGuerraRepository.findAll();

        // Construir los strings con nombres de guerreros para cada vehículo
        vehiculos.forEach(vehiculo -> {
            String guerrerosNombres = vehiculo.getGuerreros().stream()
                .map(g -> g.getNombre())
                .collect(Collectors.joining(", "));
            //vehiculo.setGuerrerosNombres(guerrerosNombres);
        });

        model.addAttribute("vehiculos", vehiculos);

        try {
            List<String> log = batallaService.iniciarBatallaPorTurnos(idVehiculo1, idVehiculo2);
            model.addAttribute("logBatalla", log);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            logger.error("Error al iniciar la batalla: {}", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "Error interno del servidor: " + e.getMessage());
            logger.error("Error al iniciar la batalla: {}", e.getMessage());
        }

        return "batalla"; // volvemos a la misma vista
    }
}