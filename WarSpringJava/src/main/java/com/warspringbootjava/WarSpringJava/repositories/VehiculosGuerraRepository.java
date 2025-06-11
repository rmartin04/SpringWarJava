package com.warspringbootjava.WarSpringJava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.warspringbootjava.WarSpringJava.entities.VehiculoGuerra;

public interface VehiculosGuerraRepository extends JpaRepository<VehiculoGuerra, Long> {
	
	List<VehiculoGuerra> findByTipoVehiculo(String tipo);
	
    List<VehiculoGuerra> findByNombreVehiculoContainingIgnoreCase(String nombre);
    
    List<VehiculoGuerra> findByTipoVehiculoAndNombreVehiculoContainingIgnoreCase(String tipo, String nombre);
    
    long countByTipoVehiculo(String tipo);

}
