package com.warspringbootjava.WarSpringJava.excepciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class HibernateSessionException extends Exception {
	
	private static final Logger logger = LoggerFactory.getLogger(HibernateSessionException.class);
	/**
	 * Excepción personalizada para manejar errores relacionados con la sesión de Hibernate.
	 * @param mensaje
	 */
	public HibernateSessionException(String mensaje) {
		super(mensaje);
		logger.error("Error en la sesión de Hibernate: {}", mensaje);
	}
	
}
