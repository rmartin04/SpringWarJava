package com.warspringbootjava.WarSpringJava.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class UsuarioNoEncontradoException extends Exception {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioNoEncontradoException.class);
	private static final long serialVersionUID = 1L;
	
	/**
	 * Excepción personalizada para indicar que un usuario no ha sido encontrado.
	 * 
	 * @param mensaje Mensaje de error que describe la situación.
	 */
	public UsuarioNoEncontradoException(String mensaje) {
        super(mensaje);
        logger.error("Usuario no encontrado: {}", mensaje);
    }
	

}
