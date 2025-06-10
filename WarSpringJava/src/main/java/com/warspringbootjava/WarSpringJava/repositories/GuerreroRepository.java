package com.warspringbootjava.WarSpringJava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warspringbootjava.WarSpringJava.entities.Guerrero;

public interface GuerreroRepository extends JpaRepository<Guerrero, Long> {
	
	List<Guerrero> findByTipo(String tipo);
	
    List<Guerrero> findByNombreContainingIgnoreCase(String nombre);
    
    List<Guerrero> findByTipoAndNombreContainingIgnoreCase(String tipo, String nombre);


}
