package com.warsrpingbootjava.WarSpringJava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warsrpingbootjava.WarSpringJava.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	// Aquí puedes definir métodos personalizados si es necesario
	// Por ejemplo, para buscar usuarios por nombre de usuario o correo electrónico

	Usuario findByUsuarioAndContrasenia(String usuario, String contrasenia);
}
