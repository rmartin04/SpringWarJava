package com.warspringbootjava.WarSpringJava.excepciones;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class FuerzaYResistenciaException extends Exception {

	    private static final Logger logger = LoggerFactory.getLogger(FuerzaYResistenciaException.class);
	    
	        /**
	         * Excepción personalizada para manejar errores de fuerza y resistencia insuficiente.
	         * @param mensaje
	         */
    public FuerzaYResistenciaException(String mensaje) {
        super(mensaje);
        logger.error("Fuerza y resistencia insuficiente: {}", mensaje);
    }

}
