package com.warsrpingbootjava.WarSpringJava.beans;

public class UsuarioDTO {
	
	private Long id;
	private String usuario;
	private String email;
	private String contrasenia;
	
	// Constructores
	public UsuarioDTO() {}
	
	public UsuarioDTO(String email, String contrasenia) {
		super();
		this.email = email;
		this.contrasenia = contrasenia;
	}
	
	public UsuarioDTO(String email, String contrasenia, String usuario) {
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
