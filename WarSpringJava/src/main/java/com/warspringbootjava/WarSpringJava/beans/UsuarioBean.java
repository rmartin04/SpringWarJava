package com.warspringbootjava.WarSpringJava.beans;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioBean {
	
	private Long id;
	
	@NotBlank(message = "El usuario no puede estar vacío")
	private String usuario;
	
	@Email(message = "El email debe ser válido")
	@NotBlank(message = "El email no puede estar vacío")
	private String email;
	
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String contrasenia;
	
	// Constructores
	public UsuarioBean() {}
	
	public UsuarioBean(String email, String contrasenia) {
		super();
		this.email = email;
		this.contrasenia = contrasenia;
	}
	
	public UsuarioBean(String email, String contrasenia, String usuario) {
		this(email, contrasenia);
		this.usuario = usuario;
	}
	
	// Getter and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("usuario: ").append(usuario).append("\n");
		sb.append("Email: ").append(email).append("\n");
		sb.append("Contraseña: ").append(contrasenia);
		return sb.toString();
	}
}
