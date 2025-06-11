package com.warspringbootjava.WarSpringJava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warspringbootjava.WarSpringJava.entities.Guerrero;

public interface GuerreroRepository extends JpaRepository<Guerrero, Long> {
	
	/** * Busca guerreros por tipo.
	 * 
	 * @param tipo el tipo de guerrero
	 * @return lista de guerreros del tipo especificado
	 */
	List<Guerrero> findByTipo(String tipo);
	
	/**
	 * 
	 * @param nombre
	 * @return lista de guerreros cuyo nombre contiene el texto especificado, ignorando mayúsculas y minúsculas
	 */
    List<Guerrero> findByNombreContainingIgnoreCase(String nombre);
    
    /**
	 * Busca guerreros por tipo y nombre.
	 * 
	 * @param tipo el tipo de guerrero
	 * @param nombre el nombre del guerrero
	 * @return lista de guerreros que coinciden con el tipo y cuyo nombre contiene el texto especificado, ignorando mayúsculas y minúsculas
	 */
    List<Guerrero> findByTipoAndNombreContainingIgnoreCase(String tipo, String nombre);
    
    /** * Cuenta el número de guerreros de un tipo específico.
	 * 
	 * @param tipo el tipo de guerrero
	 * @return el número de guerreros del tipo especificado
	 */
    long countByTipo(String tipo);


}
