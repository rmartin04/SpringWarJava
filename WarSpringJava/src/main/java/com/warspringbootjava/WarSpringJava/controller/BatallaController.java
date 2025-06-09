
package com.warsrpingbootjava.WarSpringJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.warsrpingbootjava.WarSpringJava.service.BatallaService;

@Controller
@RequestMapping("/batalla")
public class BatallaController {

    @Autowired
    private BatallaService batallaService;

    @GetMapping("/iniciar")
    public ResponseEntity<String> empezarBatalla(@RequestParam Long idVehiculo1, @RequestParam Long idVehiculo2) {
        try {
            String resultado = batallaService.iniciarBatalla(idVehiculo1, idVehiculo2);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor: " + e.getMessage());
        }
    }
}