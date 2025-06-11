package com.warspringbootjava.WarSpringJava.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class AtaqueDefensaException extends Exception {
	  private static final Logger logger = LoggerFactory.getLogger(AtaqueDefensaException.class);
	  /**
	   * Excepción personalizada para errores en ataque o defensa.
	   * @param mensaje
	   */
    public AtaqueDefensaException(String mensaje) {
        super(mensaje);
        logger.error("Error en ataque o defensa: {}", mensaje);
    }
}
