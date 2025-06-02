package com.warsrpingbootjava.WarSpringJava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warsrpingbootjava.WarSpringJava.entities.Alienigenas;

public interface AlienigenasRepository extends JpaRepository<Alienigenas, Long> {
	// This interface will automatically provide CRUD operations for Alienigenas
	// entities
	// No additional methods are needed unless specific queries are required

}
