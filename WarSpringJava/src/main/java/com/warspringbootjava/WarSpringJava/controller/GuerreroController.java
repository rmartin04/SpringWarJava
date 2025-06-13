
package com.warspringbootjava.WarSpringJava.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{id}/editar")
	public String editarGuerrero(@PathVariable Long id, Model model) {
	    Guerrero guerrero = guerreroService.obtenerGuerreroPorId(id)
	        .orElseThrow(() -> new RuntimeException("Guerrero no encontrado"));
	    model.addAttribute("guerrero", guerrero);
	    return "editar-guerrero"; // nombre del HTML
	}
	
	@PostMapping("/{id}/editar")
	public String guardarCambiosGuerrero(
	        @PathVariable Long id,
	        @Valid @ModelAttribute("guerrero") Guerrero guerrero,
	        BindingResult result,
	        Model model) {

	    if (result.hasErrors()) {
	        model.addAttribute("error", "Corrige los datos del guerrero.");
	        return "editar-guerrero";
	    }

	    try {
	        guerrero.setId(id); // aseguramos que se use el ID del path
	        guerreroService.actualizarGuerrero(guerrero);
	        model.addAttribute("success", "Guerrero actualizado correctamente.");
	        return "redirect:/guerreros/listado";
	    } catch (Exception e) {
	        model.addAttribute("error", "Error al actualizar el guerrero: " + e.getMessage());
	        return "editar-guerrero";
	    }
	}
	
	@GetMapping("/{id}/eliminar")
	public String eliminarGuerrero(@PathVariable Long id, Model model) {
	    try {
	        guerreroService.eliminarGuerrero(id);
	        model.addAttribute("success", "Guerrero eliminado correctamente.");
	    } catch (Exception e) {
	        model.addAttribute("error", "Error al eliminar el guerrero: " + e.getMessage());
	    }
	    return "redirect:/guerreros/listado";
	}

}