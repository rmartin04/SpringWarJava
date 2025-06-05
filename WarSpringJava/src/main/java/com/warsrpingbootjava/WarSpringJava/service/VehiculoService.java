
package com.warsrpingbootjava.WarSpringJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.warsrpingbootjava.WarSpringJava.entities.VehiculosGuerra;
import com.warsrpingbootjava.WarSpringJava.repositories.VehiculosGuerraRepository;

import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    private VehiculosGuerraRepository vehiculosGuerraRepository;

    public VehiculosGuerra crearVehiculo(VehiculosGuerra vehiculo) {
        return vehiculosGuerraRepository.save(vehiculo);
    }

    public VehiculosGuerra obtenerVehiculoPorId(Long id) {
        return vehiculosGuerraRepository.findById(id).orElse(null);
    }

    public List<VehiculosGuerra> listarVehiculos() {
        return vehiculosGuerraRepository.findAll();
    }

    public VehiculosGuerra embarcarGuerreros(Long vehiculoId, List<Long> guerreroIds) {
        VehiculosGuerra vehiculo = obtenerVehiculoPorId(vehiculoId);
        if (vehiculo != null) {
            // Lógica para embarcar guerreros en el vehículo
            // Por ejemplo, agregar guerreros a una lista en el vehículo
            return vehiculosGuerraRepository.save(vehiculo);
        }
        return null;
    }

    public void eliminarVehiculo(Long id) {
        vehiculosGuerraRepository.deleteById(id);
    }

	public void guardarVehiculo(VehiculosGuerra vehiculo) {
		vehiculosGuerraRepository.save(vehiculo);
	}
}