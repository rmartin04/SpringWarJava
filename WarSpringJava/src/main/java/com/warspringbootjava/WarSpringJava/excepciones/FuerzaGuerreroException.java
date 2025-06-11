package com.warspringbootjava.WarSpringJava.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FuerzaGuerreroException extends Exception{
	    private static final Logger logger = LoggerFactory.getLogger(FuerzaGuerreroException.class);

	    
	    /**
	     * Excepción para manejar errores de fuerza de guerrero insuficiente.
	     * @param mensaje
	     */
    public FuerzaGuerreroException(String mensaje) {
        super(mensaje);
        logger.error("Fuerza de guerrero insuficiente: {}", mensaje);
    }

}
