
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.warspringbootjava.WarSpringJava.entities.Guerrero;
import com.warspringbootjava.WarSpringJava.entities.VehiculoGuerra;
import com.warspringbootjava.WarSpringJava.excepciones.AtaqueDefensaException;
import com.warspringbootjava.WarSpringJava.excepciones.AtaqueMuyPoderosoException;
import com.warspringbootjava.WarSpringJava.excepciones.DefensaMuyPoderosaException;
import com.warspringbootjava.WarSpringJava.excepciones.EmbarcarGuerrerosException;
import com.warspringbootjava.WarSpringJava.excepciones.VidaMaximaPermitidaException;
import com.warspringbootjava.WarSpringJava.service.GuerreroService;
import com.warspringbootjava.WarSpringJava.service.VehiculoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(VehiculoController.class);

	private final VehiculoService vehiculoService;
	private final GuerreroService guerreroService;

	public VehiculoController(VehiculoService vehiculoService, GuerreroService guerreroService) {
		this.vehiculoService = vehiculoService;
		this.guerreroService = guerreroService;
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
	
	@GetMapping("/{id}/embarcar")
	public String mostrarFormularioEmbarcar(@PathVariable Long id, Model model) {
	    VehiculoGuerra vehiculo = vehiculoService.obtenerVehiculoPorId(id);
	    List<Guerrero> guerrerosDisponibles = guerreroService.listarGuerrerosSinVehiculo();

	    model.addAttribute("vehiculo", vehiculo);
	    model.addAttribute("guerreros", guerrerosDisponibles);
	    return "embarcar-guerreros";
	}

	@PostMapping("/{id}/embarcar")
	public String embarcarGuerreros(
	        @PathVariable Long id,
	        @RequestParam(name = "guerreroIds", required=false) List<Long> guerreroIds,
	        RedirectAttributes redirectAttributes) throws EmbarcarGuerrerosException {
		
	    try {
	        vehiculoService.embarcarGuerreros(id, guerreroIds);
	        redirectAttributes.addFlashAttribute("success", "Guerreros embarcados correctamente.");
	    } catch (EmbarcarGuerrerosException e) {
	        redirectAttributes.addFlashAttribute("error", e.getMessage());
	        return "redirect:/vehiculos/" + id + "/embarcar";
	    }
	    
	    return "redirect:/vehiculos/" + id;

	}
	
	@GetMapping("/{id}/editar")
	public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
	    VehiculoGuerra vehiculo = vehiculoService.obtenerVehiculoPorId(id);
	    
	    model.addAttribute("vehiculo", vehiculo);
	    return "editar-vehiculo"; // nombre de la plantilla HTML
	}
	
	@PostMapping("/{id}/editar")
	public String guardarCambios(
	    @PathVariable Long id,
	    @Valid @ModelAttribute("vehiculo") VehiculoGuerra vehiculo,
	    BindingResult result,
	    Model model
	) {
	    if (result.hasErrors()) {
	        model.addAttribute("error", "Corrige los datos del vehículo.");
	        return "editar-vehiculo";
	    }

	    try {
	        vehiculo.setId(id); // aseguramos ID
	        vehiculoService.actualizarVehiculo(vehiculo);
	        return "redirect:/vehiculos/listado";
	    } catch (Exception e) {
	        model.addAttribute("error", "Error al actualizar vehículo: " + e.getMessage());
	        return "editar-vehiculo";
	    }
	}
	
	@GetMapping("/{id}/eliminar")
	public String eliminarVehiculo(@PathVariable Long id, Model model) {
	    try {
	        vehiculoService.eliminarVehiculo(id);
	    } catch (Exception e) {
	        model.addAttribute("error", "No se puede eliminar el vehículo: " + e.getMessage());
	    }
	    return "redirect:/vehiculos/listado";
	}

	
	@GetMapping("/api")
	@ResponseBody
	public List<VehiculoGuerra> obtenerVehiculosApi() {
	    return vehiculoService.listarVehiculos();
	}
}
