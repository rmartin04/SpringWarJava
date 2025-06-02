package com.warsrpingbootjava.WarSpringJava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.warsrpingbootjava.WarSpringJava.service.JavaWarService;

@Controller
public class UsuarioController {
	
	@Autowired
	private JavaWarService warService;

}
