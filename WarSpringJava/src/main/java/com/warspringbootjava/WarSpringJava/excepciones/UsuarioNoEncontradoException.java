package com.warsrpingbootjava.WarSpringJava.excepciones;

public class UsuarioNoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UsuarioNoEncontradoException(String mensaje) {
        super(mensaje);
    }

}
