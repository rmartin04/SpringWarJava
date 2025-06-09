package com.warspringbootjava.WarSpringJava;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.warspringbootjava.WarSpringJava.beans.UsuarioBean;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllerAdvice {

	    /**
	     * Cada vez que un controlador maneje una petición,
	     * Thymeleaf tendrá disponible ${usuarioLogueado} en el modelo.
	     */
	    @ModelAttribute("usuarioLogueado")
	    public UsuarioBean usuarioEnSesion(HttpSession session) {
	        // De la sesión tomas el atributo que guardaste en /login
	        Object obj = session.getAttribute("usuarioLogueado");
	        return (obj instanceof UsuarioBean) ? (UsuarioBean) obj : null;
	    }
}
