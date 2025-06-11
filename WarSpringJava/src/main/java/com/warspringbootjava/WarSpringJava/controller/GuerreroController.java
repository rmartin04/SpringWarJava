
package com.warspringbootjava.WarSpringJava.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.warspringbootjava.WarSpringJava.entities.Guerrero;
import com.warspringbootjava.WarSpringJava.excepciones.FuerzaYResistenciaException;
import com.warspringbootjava.WarSpringJava.service.GuerreroService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/guerreros")
public class GuerreroController {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GuerreroController.class);

	private final GuerreroService guerreroService;

	public GuerreroController(GuerreroService guerreroService) {
		this.guerreroService = guerreroService;
	}

	@GetMapping("/listado")
	public String listarGuerreros(Model model) {
		List<Guerrero> guerreros = guerreroService.listarGuerreros();
		model.addAttribute("guerreros", guerreros);
		return "listado-guerreros";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("guerrero", new Guerrero());
		return "crear-guerrero";
	}

	@PostMapping
	public String crearGuerrero(@Valid @ModelAttribute("guerrero") Guerrero guerrero, BindingResult br, Model model) {
		if (br.hasErrors()) {
			model.addAttribute("error", "Corrige los datos del guerrero.");
			return "crear-guerrero";
		}
		try {
			guerreroService.crearGuerrero(guerrero);
			model.addAttribute("success", guerrero.getTipo() + " creado exitosamente.");
			logger.info("Guerrero creado: {}", guerrero);
			model.addAttribute("guerrero", new Guerrero()); // limpia el formulario
		} catch (FuerzaYResistenciaException e) {
			model.addAttribute("error", e.getMessage());
			logger.error("Error al crear guerrero: {}", e.getMessage());
		} catch (Exception e) {
			model.addAttribute("error", "Error al crear el guerrero: " + e.getMessage());
			logger.error("Error al crear guerrero: {}", e.getMessage());
		}
		return "crear-guerrero";
	}

}