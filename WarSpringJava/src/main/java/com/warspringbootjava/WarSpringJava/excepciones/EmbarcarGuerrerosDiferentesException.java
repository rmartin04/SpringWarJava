package com.warspringbootjava.WarSpringJava.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Excepción lanzada cuando se intenta embarcar guerreros de diferentes tipos.
 * Esta excepción se utiliza para indicar que no se pueden mezclar guerreros de
 * diferentes clases en una misma operación de embarque.
 */

public class EmbarcarGuerrerosDiferentesException extends Exception {
	  private static final Logger logger = LoggerFactory.getLogger(EmbarcarGuerrerosDiferentesException.class);

    public EmbarcarGuerrerosDiferentesException(String mensaje) {
        super(mensaje);
        logger.error("Error al embarcar guerreros: {}", mensaje);
    }

}
