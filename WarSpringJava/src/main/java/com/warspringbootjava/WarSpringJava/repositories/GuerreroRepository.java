package com.warsrpingbootjava.WarSpringJava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warsrpingbootjava.WarSpringJava.entities.Guerrero;

public interface GuerreroRepository extends JpaRepository<Guerrero, Long> {
	

}
