package com.warspringbootjava.WarSpringJava.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class EmbarcarGuerrerosException extends Exception {
	
	private static final Logger logger = LoggerFactory.getLogger(EmbarcarGuerrerosException.class);
	
	/**
	 * Excepción para manejar errores al embarcar guerreros.
	 * @param mensaje
	 */
    public EmbarcarGuerrerosException(String mensaje) {
        super(mensaje);
        logger.error("Error al embarcar guerreros: {}", mensaje);
    }

}
