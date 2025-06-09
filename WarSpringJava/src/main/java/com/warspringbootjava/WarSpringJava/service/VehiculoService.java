
package com.warspringbootjava.WarSpringJava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warspringbootjava.WarSpringJava.entities.Guerrero;
import com.warspringbootjava.WarSpringJava.entities.VehiculosGuerra;
import com.warspringbootjava.WarSpringJava.repositories.GuerreroRepository;
import com.warspringbootjava.WarSpringJava.repositories.VehiculosGuerraRepository;

@Service
public class VehiculoService {

    @Autowired
    private VehiculosGuerraRepository vehiculosGuerraRepository;

    @Autowired
    private GuerreroRepository guerreroRepository;

    // Guarda o actualiza un vehículo
    public VehiculosGuerra guardarVehiculo(VehiculosGuerra vehiculo) {
        return vehiculosGuerraRepository.save(vehiculo);
    }

    // Obtiene un vehículo por su ID
    public Optional<VehiculosGuerra> obtenerVehiculoPorId(Long id) {
        return vehiculosGuerraRepository.findById(id);
    }

    // Lista todos los vehículos
    public List<VehiculosGuerra> listarVehiculos() {
        return vehiculosGuerraRepository.findAll();
    }

    public VehiculosGuerra embarcarGuerreros(Long vehiculoId, List<Long> guerreroIds) {
        // Buscar el vehículo
        Optional<VehiculosGuerra> optionalVehiculo = obtenerVehiculoPorId(vehiculoId);

        if (optionalVehiculo.isPresent()) {
            VehiculosGuerra vehiculo = optionalVehiculo.get();

            // Buscar los guerreros por sus IDs
            List<Guerrero> guerreros = guerreroRepository.findAllById(guerreroIds);

            // Agregar los guerreros al vehículo
            vehiculo.getGuerreros().addAll(guerreros);

            // Guardar el vehículo actualizado
            return vehiculosGuerraRepository.save(vehiculo);
        }

        return null;
    }

    public void eliminarVehiculo(Long id) {
        vehiculosGuerraRepository.deleteById(id);
    }

	
}