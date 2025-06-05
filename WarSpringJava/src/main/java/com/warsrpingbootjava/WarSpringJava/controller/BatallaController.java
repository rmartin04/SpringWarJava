
package com.warsrpingbootjava.WarSpringJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.warsrpingbootjava.WarSpringJava.entities.VehiculosGuerra;
import com.warsrpingbootjava.WarSpringJava.service.BatallaService;


@Controller
@RequestMapping("/batallas")
public class BatallaController {

    @Autowired
    private BatallaService batallaService;

    @PostMapping("/iniciar")
    public String empezarBatalla(@RequestParam Long idVehiculo1, @RequestParam Long idVehiculo2) {
    	
        return batallaService.iniciarBatalla(idVehiculo1, idVehiculo2);
    }

   
}