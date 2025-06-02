package com.warsrpingbootjava.WarSpringJava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warsrpingbootjava.WarSpringJava.entities.Humanos;

public interface HumanosRepository extends JpaRepository<Humanos, Long> {
	// This interface will automatically provide CRUD operations for Humanos
	// entities
	// No additional methods are needed unless specific queries are required

}
