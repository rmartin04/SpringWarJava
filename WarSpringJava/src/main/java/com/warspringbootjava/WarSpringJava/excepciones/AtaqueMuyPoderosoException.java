package com.warspringbootjava.WarSpringJava.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class AtaqueMuyPoderosoException extends Exception {
	  private static final Logger logger = LoggerFactory.getLogger(AtaqueMuyPoderosoException.class);
    
		/**
		 * Excepción para ataques que superan el límite de poder.
		 * 
		 * @param mensaje Mensaje de error que describe la situación.
		 */
    public AtaqueMuyPoderosoException(String mensaje) {
        super(mensaje);
        logger.error("Ataque muy poderoso: {}", mensaje);
    }
}
