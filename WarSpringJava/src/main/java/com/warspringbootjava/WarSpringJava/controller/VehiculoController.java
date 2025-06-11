
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.warspringbootjava.WarSpringJava.entities.VehiculoGuerra;
import com.warspringbootjava.WarSpringJava.excepciones.AtaqueDefensaException;
import com.warspringbootjava.WarSpringJava.excepciones.AtaqueMuyPoderosoException;
import com.warspringbootjava.WarSpringJava.excepciones.DefensaMuyPoderosaException;
import com.warspringbootjava.WarSpringJava.excepciones.EmbarcarGuerrerosException;
import com.warspringbootjava.WarSpringJava.excepciones.VidaMaximaPermitidaException;
import com.warspringbootjava.WarSpringJava.service.VehiculoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(VehiculoController.class);

	private final VehiculoService vehiculoService;

	public VehiculoController(VehiculoService vehiculoService) {
		this.vehiculoService = vehiculoService;
	}

	@GetMapping("/listado")
	public String listarVehiculos(Model model) {
		List<VehiculoGuerra> vehiculos = vehiculoService.listarVehiculos();
		model.addAttribute("vehiculos", vehiculos);
		return "listado-vehiculos";
	}

	@GetMapping("/nuevo")
	public String mostrarFormulario(Model model) {
		model.addAttribute("vehiculo", new VehiculoGuerra());
		return "crear-vehiculo";
	}

	@PostMapping
	public String crearVehiculo(@Valid @ModelAttribute("vehiculo") VehiculoGuerra vehiculo,
	                            BindingResult br,
	                            Model model) {
		if (br.hasErrors()) {
			model.addAttribute("error", "Corrige los datos del vehículo.");
			return "crear-vehiculo";
		}

		try {
			vehiculoService.guardarVehiculo(vehiculo);
			model.addAttribute("success", "Vehículo creado exitosamente.");
			logger.info("Vehículo creado: {}", vehiculo);
			model.addAttribute("vehiculo", new VehiculoGuerra()); // limpia el formulario
		} catch (VidaMaximaPermitidaException | AtaqueDefensaException | AtaqueMuyPoderosoException |
		         DefensaMuyPoderosaException | EmbarcarGuerrerosException e) {
			model.addAttribute("error", e.getMessage());
			logger.error("Error al crear vehículo: {}", e.getMessage());
		} catch (Exception e) {
			model.addAttribute("error", "Error al crear el vehículo: " + e.getMessage());
			logger.error("Error general: {}", e.getMessage());
		}

		return "crear-vehiculo";
	}

	@GetMapping("/{id}")
	public String verVehiculo(@PathVariable Long id, Model model) {
		try {
			VehiculoGuerra vehiculo = vehiculoService.obtenerVehiculoPorId(id);
			model.addAttribute("vehiculo", vehiculo);
			return "detalle-vehiculo";
		} catch (Exception e) {
			model.addAttribute("error", "Vehículo no encontrado.");
			logger.error("Error al buscar vehículo por ID {}: {}", id, e.getMessage());
			return "redirect:/vehiculos/listado";
		}
	}
	
	@GetMapping("/api")
	@ResponseBody
	public List<VehiculoGuerra> obtenerVehiculosApi() {
	    return vehiculoService.listarVehiculos();
	}
}
