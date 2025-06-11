package com.warspringbootjava.WarSpringJava.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class VidaMaximaPermitidaException extends Exception{
	    private static final Logger logger = LoggerFactory.getLogger(VidaMaximaPermitidaException.class);
	    
	    /**
	     * Excepción que se lanza cuando la vida máxima permitida es superada.
	     * @param mensaje
	     */
    public VidaMaximaPermitidaException(String mensaje) {
        super(mensaje);	
        logger.error("Vida máxima permitida superada: {}", mensaje);
    }
    
}
