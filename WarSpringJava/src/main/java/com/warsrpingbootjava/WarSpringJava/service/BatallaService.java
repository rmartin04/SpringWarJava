
package com.warsrpingbootjava.WarSpringJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.warsrpingbootjava.WarSpringJava.entities.VehiculosGuerra;
import com.warsrpingbootjava.WarSpringJava.repositories.VehiculosGuerraRepository;

import java.util.Random;

@Service
public class BatallaService {

    @Autowired
    private VehiculosGuerraRepository vehiculosGuerraRepository;

    /**
     * Simula una batalla entre dos vehículos de guerra.
     * @param vehiculoId1 ID del primer vehículo.
     * @param vehiculoId2 ID del segundo vehículo.
     * @return El vehículo ganador o null si hay empate.
     */
    public String iniciarBatalla(Long vehiculoId1, Long vehiculoId2) {
        VehiculosGuerra vehiculo1 = vehiculosGuerraRepository.findById(vehiculoId1).orElse(null);
        VehiculosGuerra vehiculo2 = vehiculosGuerraRepository.findById(vehiculoId2).orElse(null);
        String ganador = null;

        if (vehiculo1 == null || vehiculo2 == null) {
            throw new IllegalArgumentException("Uno o ambos vehículos no existen.");
        }

        int ataqueVehiculo1 = calcularAtaque(vehiculo1);
        int defensaVehiculo2 = calcularDefensa(vehiculo2, ataqueVehiculo1);

        int ataqueVehiculo2 = calcularAtaque(vehiculo2);
        int defensaVehiculo1 = calcularDefensa(vehiculo1, ataqueVehiculo2);

        // Calcular daño recibido
        int danoVehiculo1 = Math.max(0, ataqueVehiculo2 - defensaVehiculo1);
        int danoVehiculo2 = Math.max(0, ataqueVehiculo1 - defensaVehiculo2);

        vehiculo1.setPuntosVida(vehiculo1.getPuntosVida() - danoVehiculo1);
        vehiculo2.setPuntosVida(vehiculo2.getPuntosVida() - danoVehiculo2);

        // Guardar los cambios en la base de datos
        vehiculosGuerraRepository.save(vehiculo1);
        vehiculosGuerraRepository.save(vehiculo2);

        // Determinar el ganador
        if (vehiculo1.getPuntosVida() > vehiculo2.getPuntosVida()) {
        	ganador = vehiculo1.getNombreVehiculo() + " ha ganado la batalla.";
            return ganador;
        } else if (vehiculo2.getPuntosVida() > vehiculo1.getPuntosVida()) {
        	ganador = vehiculo2.getNombreVehiculo() + " ha ganado la batalla.";
            return ganador;
        } else {
        	ganador = "La batalla ha terminado en empate.";
            return ganador; // Empate
        }
    }

    private int calcularAtaque(VehiculosGuerra vehiculo) {
        Random random = new Random();
        double factorVehiculo = random.nextDouble();
        double ataqueVehiculo = vehiculo.getAtaqueBase() * factorVehiculo;

        double factorGuerreros = random.nextDouble() * 0.5;
        int sumaAtaqueGuerreros = vehiculo.getGuerreros().stream()
                .mapToInt(guerrero -> guerrero.getFuerzaBase())
                .sum();
        double ataqueGuerreros = sumaAtaqueGuerreros * factorGuerreros;

        return (int) Math.round(ataqueVehiculo + ataqueGuerreros);
    }

    private int calcularDefensa(VehiculosGuerra vehiculo, int ataqueEntrante) {
        Random random = new Random();
        double factorVehiculo = random.nextDouble();
        double defensaVehiculo = vehiculo.getDefensaBase() * factorVehiculo;

        double factorGuerreros = random.nextDouble() * 0.5;
        int sumaResistenciaGuerreros = vehiculo.getGuerreros().stream()
                .mapToInt(guerrero -> guerrero.getResistencia())
                .sum();
        double defensaGuerreros = sumaResistenciaGuerreros * factorGuerreros;

        return (int) Math.round(defensaVehiculo + defensaGuerreros);
    }
}