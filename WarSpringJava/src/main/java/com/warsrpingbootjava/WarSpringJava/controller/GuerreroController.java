
package com.warsrpingbootjava.WarSpringJava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.warsrpingbootjava.WarSpringJava.entities.Guerrero;
import com.warsrpingbootjava.WarSpringJava.service.GuerreroService;

@Controller
@RequestMapping("/guerreros")
public class GuerreroController {

    @Autowired
    private GuerreroService guerreroService;

    @PostMapping("/crear")
    public Guerrero crearGuerrero(@RequestBody Guerrero guerrero) {
        return guerreroService.crearGuerrero(guerrero);
    }

    @GetMapping("/{id}")
    public Guerrero obtenerGuerrero(@PathVariable Long id) {
        return guerreroService.obtenerGuerreroPorId(id);
    }

    @GetMapping("/listar")
    public List<Guerrero> listarGuerreros() {
        return guerreroService.listarGuerreros();
    }
    @GetMapping("/guerrero/nuevo")
    public String mostrarFormularioGuerrero(Model model) {
        model.addAttribute("guerrero", new Guerrero());
        return "guerrero-form";
    }

@PostMapping("/guerrero/guardar")
public String guardarGuerrero(@ModelAttribute Guerrero guerrero) {
    guerreroService.guardar(guerrero);
    return "redirect:/guerreros";
}

}