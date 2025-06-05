
package com.warsrpingbootjava.WarSpringJava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.warsrpingbootjava.WarSpringJava.entities.VehiculosGuerra;
import com.warsrpingbootjava.WarSpringJava.service.VehiculoService;

@Controller
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/crear")
    public VehiculosGuerra crearVehiculo(@RequestBody VehiculosGuerra vehiculo) {
        return vehiculoService.crearVehiculo(vehiculo);
    }

    @GetMapping("/{id}")
    public VehiculosGuerra obtenerVehiculo(@PathVariable Long id) {
        return vehiculoService.obtenerVehiculoPorId(id);
    }

    @GetMapping("/listar")
    public List<VehiculosGuerra> listarVehiculos() {
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
