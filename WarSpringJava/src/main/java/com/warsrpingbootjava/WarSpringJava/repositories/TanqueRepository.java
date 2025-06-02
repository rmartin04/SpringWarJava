package com.warsrpingbootjava.WarSpringJava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warsrpingbootjava.WarSpringJava.entities.Tanque;

public interface TanqueRepository extends JpaRepository<Tanque, Long> {
	// Aquí puedes agregar métodos personalizados si es necesario
	// Por ejemplo, para buscar tanques por su tipo o estado específico
	// public List<Tanque> findByTipo(String tipo);
	// public List<Tanque> findByEstado(String estado);

}
