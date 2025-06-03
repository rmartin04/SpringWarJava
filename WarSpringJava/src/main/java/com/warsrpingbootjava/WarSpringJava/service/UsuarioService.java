package com.warsrpingbootjava.WarSpringJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warsrpingbootjava.WarSpringJava.beans.UsuarioBean;
import com.warsrpingbootjava.WarSpringJava.entities.Usuario;
import com.warsrpingbootjava.WarSpringJava.excepciones.UsuarioNoEncontradoException;
import com.warsrpingbootjava.WarSpringJava.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioBean usuarioBean;
	
	public UsuarioBean validarUsuario(String usuario, String contrasenia) {	
		Usuario userValido = usuarioRepository.findByUsuarioAndContrasenia(usuario, contrasenia);	
        if (usuario == null || contrasenia == null) {
            throw new UsuarioNoEncontradoException("Usuario o contraseña no pueden ser nulos");
        }
        
        if (userValido == null) {
			throw new UsuarioNoEncontradoException("Usuario o contraseña incorrectos");
		}
		
		return convertirAUsuarioBean(userValido);
	}
	
	public UsuarioBean convertirAUsuarioBean(Usuario usuario) {
		if (usuario != null) {
			usuarioBean.setUsuario(usuario.getUsuario());
			usuarioBean.setContrasenia(usuario.getContrasenia());
			return usuarioBean;
		} else {
			throw new IllegalArgumentException("El usuario no puede ser nulo");
		}
	}
	
	public UsuarioBean registrarUsuario(UsuarioBean usuarioBean) {
		
		if (usuarioBean == null || usuarioBean.getUsuario() == null || usuarioBean.getContrasenia() == null) {
			throw new IllegalArgumentException("El usuario o la contraseña no pueden ser nulos");
		}
		
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setUsuario(usuarioBean.getUsuario());
		nuevoUsuario.setEmail(usuarioBean.getEmail());
		nuevoUsuario.setContrasenia(usuarioBean.getContrasenia());
		
		usuarioRepository.save(nuevoUsuario);
		
		return convertirAUsuarioBean(nuevoUsuario);
	}

}
