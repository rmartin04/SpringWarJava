package com.warspringbootjava.WarSpringJava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warspringbootjava.WarSpringJava.entities.Guerrero;

public interface GuerreroRepository extends JpaRepository<Guerrero, Long> {
	

}
