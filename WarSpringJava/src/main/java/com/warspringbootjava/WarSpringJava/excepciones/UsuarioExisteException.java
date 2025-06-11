package com.warspringbootjava.WarSpringJava.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class UsuarioExisteException extends Exception {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioExisteException.class);
	private static final long serialVersionUID = 1L;

	/**
	 * Excepción que se lanza cuando un usuario ya existe en el sistema.
	 * 
	 * @param message Mensaje de error que describe la situación.
	 */
	public UsuarioExisteException(String message) {
		super(message);
		logger.error("El usuario ya existe: {}", message);
	}
}
