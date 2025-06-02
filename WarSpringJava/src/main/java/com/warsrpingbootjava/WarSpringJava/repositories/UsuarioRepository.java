package com.warsrpingbootjava.WarSpringJava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.warsrpingbootjava.WarSpringJava.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	// Aquí puedes agregar métodos personalizados si es necesario
	// Por ejemplo, para buscar usuarios por su nombre o correo electrónico
	// public List<Usuario> findByNombre(String nombre);
	// public Usuario findByEmail(String email);
    // public Optional<Usuario> findByUsername(String username);
}
