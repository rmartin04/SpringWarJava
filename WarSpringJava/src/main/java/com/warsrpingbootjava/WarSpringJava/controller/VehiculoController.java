
package com.warsrpingbootjava.WarSpringJava.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.warsrpingbootjava.WarSpringJava.entities.VehiculosGuerra;
import com.warsrpingbootjava.WarSpringJava.service.VehiculoService;

@Controller
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/crear")
    public VehiculosGuerra crearVehiculo(@RequestBody VehiculosGuerra vehiculo) {
        return vehiculoService.guardarVehiculo(vehiculo); // Cambiado a guardarVehiculo
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
        return "vehiculos";
    }

    @GetMapping("/vehiculo/nuevo")
    public String mostrarFormularioVehiculo(Model model) {
        model.addAttribute("vehiculo", new VehiculosGuerra());
        return "vehiculo-form";
    }

    @PostMapping("/vehiculo/guardar")
    public String guardarVehiculo(@ModelAttribute VehiculosGuerra vehiculo) {
        vehiculoService.guardarVehiculo(vehiculo);
        return "redirect:/vehiculos";
    }
}
