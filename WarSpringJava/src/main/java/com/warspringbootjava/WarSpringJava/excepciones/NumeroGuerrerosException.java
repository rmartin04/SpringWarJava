package com.warspringbootjava.WarSpringJava.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class NumeroGuerrerosException extends Exception {

	private static final Logger logger = LoggerFactory.getLogger(NumeroGuerrerosException.class);
	private static final long serialVersionUID = 1L;

	/**
	 * Excepción personalizada para manejar errores relacionados con el número de
	 * guerreros.
	 * 
	 * @param message Mensaje de error que describe la situación.
	 */
	public NumeroGuerrerosException(String message) {
		super(message);
		logger.error("Número de guerreros inválido: {}", message);
	}


}
