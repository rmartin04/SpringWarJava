package com.warspringbootjava.WarSpringJava.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ResistenciaGuerreroException extends Exception{

	    private static final Logger logger = LoggerFactory.getLogger(ResistenciaGuerreroException.class);
	    /**
	     * Excepción para indicar que la resistencia de un guerrero es insuficiente.
	     * @param mensaje
	     */
    public ResistenciaGuerreroException(String mensaje) {
        super(mensaje);
        	logger.error("Resistencia de guerrero insuficiente: {}", mensaje);
    }
    
}
