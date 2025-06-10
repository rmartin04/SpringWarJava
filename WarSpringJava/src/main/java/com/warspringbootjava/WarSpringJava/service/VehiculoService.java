
package com.warspringbootjava.WarSpringJava.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warspringbootjava.WarSpringJava.entities.Guerrero;
import com.warspringbootjava.WarSpringJava.entities.VehiculosGuerra;
import com.warspringbootjava.WarSpringJava.excepciones.AtaqueDefensaException;
import com.warspringbootjava.WarSpringJava.excepciones.AtaqueMuyPoderosoException;
import com.warspringbootjava.WarSpringJava.excepciones.DefensaMuyPoderosaException;
import com.warspringbootjava.WarSpringJava.excepciones.EmbarcarGuerrerosException;
import com.warspringbootjava.WarSpringJava.excepciones.VidaMaximaPermitidaException;
import com.warspringbootjava.WarSpringJava.repositories.GuerreroRepository;
import com.warspringbootjava.WarSpringJava.repositories.VehiculosGuerraRepository;

@Service
public class VehiculoService {

    @Autowired
    private VehiculosGuerraRepository vehiculosGuerraRepository;

    @Autowired
    private GuerreroRepository guerreroRepository;
    
   

    // Guarda o actualiza un vehículo
    public VehiculosGuerra guardarVehiculo(VehiculosGuerra vehiculo)throws VidaMaximaPermitidaException, AtaqueDefensaException,
    AtaqueMuyPoderosoException, DefensaMuyPoderosaException, EmbarcarGuerrerosException {
    	 int puntosVida = vehiculo.getPuntosVida();
    	 String nombreVehiculo = vehiculo.getNombreVehiculo();
    	 String tipoVehiculo = vehiculo.getTipoVehiculo();
    	 int ataqueBase = vehiculo.getAtaqueBase();
    	 int defensaBase = vehiculo.getDefensaBase();
    	 List<Guerrero> guerreros = vehiculo.getGuerreros();
        if (puntosVida > 1000) {
            throw new VidaMaximaPermitidaException("No se puede tener más de 1000 puntos de vida. ¡Deja de hacer trampa tronco!");
        } else {
            puntosVida = 1000;
        }

        nombreVehiculo = nombreVehiculo;
        tipoVehiculo = tipoVehiculo;

       
		// Validaciones de ataque y defensa
        if (ataqueBase + defensaBase > 10) {
            throw new AtaqueDefensaException("La suma del ataque y la defensa no puede superar los 10 puntos. ¡Ojito que te veo!");
        }
        if (ataqueBase > 10) {
            throw new AtaqueMuyPoderosoException("El total del ataque no puede ser mayor a 10. ¡Jummm!");
        }
        if (defensaBase > 10) {
            throw new DefensaMuyPoderosaException("El total de la defensa no puede ser mayor a 10. ¡Jummm!");
        }

        // Asignación de valores con comprobación de ataque mínimo
        if (ataqueBase < 5) {
            // Si el ataque es menor a 5, se asignan valores por defecto
        	ataqueBase = 5;
            defensaBase = 5;
        } else {
            ataqueBase = ataqueBase;
            defensaBase = defensaBase;
        }
        // Validación y asignación de la lista de guerreros
        if (guerreros == null) {
           guerreros = new ArrayList<>();
        } else {
            if (guerreros.size() > 10) {
                throw new EmbarcarGuerrerosException("No se pueden embarcar más de 10 guerreros. ¡TRAMPOSO!");
            } else {
                // Se asigna una copia para evitar modificaciones externas
              guerreros = new ArrayList<>(guerreros);
            }
        }
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