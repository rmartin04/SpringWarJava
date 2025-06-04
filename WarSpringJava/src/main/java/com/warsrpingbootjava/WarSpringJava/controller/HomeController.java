package com.warsrpingbootjava.WarSpringJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.warsrpingbootjava.WarSpringJava.beans.UsuarioBean;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("usuarioBean", new UsuarioBean());
        return "login";
    }
}


