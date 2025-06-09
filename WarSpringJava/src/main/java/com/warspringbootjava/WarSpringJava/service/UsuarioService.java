package com.warspringbootjava.WarSpringJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warspringbootjava.WarSpringJava.beans.UsuarioBean;
import com.warspringbootjava.WarSpringJava.entities.Usuario;
import com.warspringbootjava.WarSpringJava.excepciones.UsuarioExisteException;
import com.warspringbootjava.WarSpringJava.excepciones.UsuarioNoEncontradoException;
import com.warspringbootjava.WarSpringJava.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/**
	 * Registra un nuevo usuario.
	 * 
	 * @param usuarioBean El objeto UsuarioBean con los datos del nuevo usuario.
	 * @return UsuarioBean con los datos del usuario registrado.
	 * @throws UsuarioExisteException Si el usuario ya existe.
	 */
	public UsuarioBean registrarUsuario(UsuarioBean usuarioBean) throws UsuarioExisteException {
		
		if (usuarioBean == null || usuarioBean.getUsuario() == null || usuarioBean.getContrasenia() == null) {
			throw new IllegalArgumentException("El usuario o la contraseña no pueden ser nulos");
		}
		
		if (usuarioRepository.findByUsuario(usuarioBean.getUsuario()) != null) {
			throw new UsuarioExisteException("El usuario ya existe");
		}
		
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setUsuario(usuarioBean.getUsuario());
		nuevoUsuario.setEmail(usuarioBean.getEmail());
		nuevoUsuario.setContrasenia(usuarioBean.getContrasenia());
		
		usuarioRepository.save(nuevoUsuario);
		
		return convertirAUsuarioBean(nuevoUsuario);
	}
	
	/**
	 * Valida un usuario y contraseña.
	 * 
	 * @param usuario    El nombre de usuario.
	 * @param contrasenia La contraseña del usuario.
	 * @return UsuarioBean con los datos del usuario si la validación es exitosa.
	 * @throws UsuarioNoEncontradoException Si el usuario o la contraseña son incorrectos.
	 * @throws UsuarioExisteException 
	 */
	public UsuarioBean validarUsuario(String usuario, String contrasenia) throws UsuarioNoEncontradoException {	
	    if (usuario == null || contrasenia == null || usuario.trim().isEmpty() || contrasenia.trim().isEmpty()) {
	        throw new IllegalArgumentException("Usuario y contraseña no pueden ser nulos o vacíos");
	    }
	    
	    if (usuarioRepository.findByUsuario(usuario) == null) {
	        throw new UsuarioNoEncontradoException("Usuario no encontrado");
	    }
	    
	    if (usuarioRepository.findByContrasenia(contrasenia) == null) {
	        throw new UsuarioNoEncontradoException("Contraseña incorrecta");
	    }

	    Usuario userValido = usuarioRepository.findByUsuarioAndContrasenia(usuario, contrasenia);

	    if (userValido == null) {
	        throw new UsuarioNoEncontradoException("Usuario o contraseña incorrectos");
	    }	        
		return convertirAUsuarioBean(userValido);
	}
	
	/**
	 * Convierte un objeto Usuario a UsuarioBean.
	 * 
	 * @param usuario El objeto Usuario a convertir.
	 * @return UsuarioBean con los datos del usuario.
	 * @throws IllegalArgumentException Si el usuario es nulo.
	 */
	public UsuarioBean convertirAUsuarioBean(Usuario usuario) {
	    if (usuario == null) {
	        throw new IllegalArgumentException("El usuario no puede ser nulo");
	    }
	    UsuarioBean bean = new UsuarioBean();
	    bean.setUsuario(usuario.getUsuario());
	    bean.setContrasenia(usuario.getContrasenia());
	    bean.setEmail(usuario.getEmail()); // recuerda setear email si existe
	    return bean;
	}
	


}
