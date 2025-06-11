
package com.warspringbootjava.WarSpringJava.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.warspringbootjava.WarSpringJava.entities.VehiculosGuerra;
import com.warspringbootjava.WarSpringJava.excepciones.AtaqueDefensaException;
import com.warspringbootjava.WarSpringJava.excepciones.AtaqueMuyPoderosoException;
import com.warspringbootjava.WarSpringJava.excepciones.DefensaMuyPoderosaException;
import com.warspringbootjava.WarSpringJava.excepciones.EmbarcarGuerrerosException;
import com.warspringbootjava.WarSpringJava.excepciones.VidaMaximaPermitidaException;
import com.warspringbootjava.WarSpringJava.service.VehiculoService;

import jakarta.validation.Valid;

@Controller
public class VehiculoController {
	    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(VehiculoController.class);

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/crear")
	public String crearVehiculo(@Valid@ModelAttribute VehiculosGuerra vehiculo,
			 BindingResult br,
		        Model model) {
    	        if (br.hasErrors()) {
    	         model.addAttribute("error", "Corrige los datos del vehículo.");
    	        logger.error("Errores en el formulario de creación de vehículo: {}", br.getAllErrors()); 
        	     return "vehiculo-form"; // Retorna al formulario con errores
    	        }
    	
		try {
			vehiculoService.guardarVehiculo(vehiculo);
			model.addAttribute("success", "Vehículo creado exitosamente.");
			logger.info("Vehículo creado: {}", vehiculo);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			logger.error("Error al crear vehículo: {}", e.getMessage());
		}
		return "redirect:/listado-vehiculos"; // Redirige a la lista de vehículos
	}

    @GetMapping("/{id}")
    public VehiculosGuerra obtenerVehiculo(@PathVariable Long id) {
        Optional<VehiculosGuerra> vehiculo = vehiculoService.obtenerVehiculoPorId(id);
        return vehiculo.orElse(null); // Manejo del Optional
    }

    @GetMapping("/listar")
    public List<VehiculosGuerra> listarTodosLosVehiculos() { // Renombrado para evitar conflicto
        return vehiculoService.listarVehiculos();
    }

    @DeleteMapping("/{id}")
    public void eliminarVehiculo(@PathVariable Long id) {
        vehiculoService.eliminarVehiculo(id);
    }

    @GetMapping("/vehiculos")
    public String listarVehiculos(Model model) {
        model.addAttribute("vehiculos", vehiculoService.listarVehiculos());
        return "listado-vehiculos";
    }

    @GetMapping("/vehiculo/nuevo")
    public String mostrarFormularioVehiculo(Model model) {
        model.addAttribute("vehiculo", new VehiculosGuerra());
        return "crear-vehiculo";
    }

    @PostMapping("/vehiculo/guardar")
    public String guardarVehiculo(@ModelAttribute VehiculosGuerra vehiculo) {
        try {
			vehiculoService.guardarVehiculo(vehiculo);
		} catch (VidaMaximaPermitidaException e) {
			logger.error("Error al guardar vehículo: {}", e.getMessage());
		} catch (AtaqueDefensaException e) {
			logger.error("Error al guardar vehículo: {}", e.getMessage());
		} catch (AtaqueMuyPoderosoException e) {
			logger.error("Error al guardar vehículo: {}", e.getMessage());
		} catch (DefensaMuyPoderosaException e) {
		    logger.error("Error al guardar vehículo: {}", e.getMessage());
		} catch (EmbarcarGuerrerosException e) {
			logger.error("Error al guardar vehículo: {}", e.getMessage());
		} catch (Exception e) {
			logger.error("Error inesperado al guardar vehículo: {}", e.getMessage());
		}
        return "redirect:/listado-vehiculos";
    }
}
