package com.warspringbootjava.WarSpringJava.beans;

import jakarta.validation.constraints.NotBlank;

public class LoginBean {

    @NotBlank(message = "El usuario es obligatorio")
    private String usuario;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasenia;

    // Getters y setters
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContrasenia() {
        return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
	
}
