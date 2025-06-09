package com.warspringbootjava.WarSpringJava.excepciones;

public class UsuarioExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioExisteException(String message) {
		super(message);
	}
}
