package com.warspringbootjava.WarSpringJava.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_USUARIOS_WAR")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USUARIO", nullable = false, unique = true)
	private String usuario;
	
	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;
	
	@Column(name = "CONTRASEÑA", nullable = false)
	private String contrasenia;

	// Constructores
	public Usuario() {}
	
	public Usuario(Long id) {
		this.id = id;
	}
	
	public Usuario(String email, String contrasenia) {
		super();
		this.email = email;
		this.contrasenia = contrasenia;
	}
	
	public Usuario(String email, String contrasenia, String usuario) {
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
