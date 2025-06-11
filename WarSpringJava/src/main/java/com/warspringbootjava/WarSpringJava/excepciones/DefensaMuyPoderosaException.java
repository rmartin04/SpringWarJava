package com.warspringbootjava.WarSpringJava.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefensaMuyPoderosaException extends Exception{
	
	private static final Logger logger = LoggerFactory.getLogger(DefensaMuyPoderosaException.class);
	/**
	 * Excepción para indicar que la defensa de un guerrero es demasiado poderosa.
	 * @param mensaje
	 */
    public DefensaMuyPoderosaException(String mensaje) {
        super(mensaje);
        logger.error("Defensa muy poderosa: {}", mensaje);
    }

}
