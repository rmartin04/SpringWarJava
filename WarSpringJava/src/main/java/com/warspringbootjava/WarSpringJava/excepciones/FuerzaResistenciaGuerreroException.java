package com.warspringbootjava.WarSpringJava.excepciones;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FuerzaResistenciaGuerreroException extends Exception {
	    private static final Logger logger = LoggerFactory.getLogger(FuerzaResistenciaGuerreroException.class);

	        /**
	         * Excepción para manejar errores de fuerza o resistencia de guerrero insuficiente.
	         * @param mensaje
	         */
    public FuerzaResistenciaGuerreroException(String mensaje) {
        super(mensaje);
        logger.error("Fuerza o resistencia de guerrero insuficiente: {}", mensaje);
    }

}
