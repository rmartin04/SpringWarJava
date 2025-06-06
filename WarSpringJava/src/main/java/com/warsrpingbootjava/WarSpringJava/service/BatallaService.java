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

    private final Random random = new Random(); // Instancia compartida de Random

    /**
     * Simula una batalla entre dos vehículos de guerra.
     * @param vehiculoId1 ID del primer vehículo.
     * @param vehiculoId2 ID del segundo vehículo.
     * @return El vehículo ganador o un mensaje de empate.
     */
    public String iniciarBatalla(Long vehiculoId1, Long vehiculoId2) {
        VehiculosGuerra vehiculo1 = vehiculosGuerraRepository.findById(vehiculoId1)
                .orElseThrow(() -> new IllegalArgumentException("El vehículo con ID " + vehiculoId1 + " no existe."));
        VehiculosGuerra vehiculo2 = vehiculosGuerraRepository.findById(vehiculoId2)
                .orElseThrow(() -> new IllegalArgumentException("El vehículo con ID " + vehiculoId2 + " no existe."));

        int ataqueVehiculo1 = calcularPuntaje(vehiculo1, "ataque");
        int defensaVehiculo2 = calcularPuntaje(vehiculo2, "defensa");

        int ataqueVehiculo2 = calcularPuntaje(vehiculo2, "ataque");
        int defensaVehiculo1 = calcularPuntaje(vehiculo1, "defensa");

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
            return vehiculo1.getNombreVehiculo() + " ha ganado la batalla.";
        } else if (vehiculo2.getPuntosVida() > vehiculo1.getPuntosVida()) {
            return vehiculo2.getNombreVehiculo() + " ha ganado la batalla.";
        } else {
            return "La batalla ha terminado en empate.";
        }
    }

    /**
     * Calcula el puntaje de ataque o defensa de un vehículo.
     * @param vehiculo El vehículo de guerra.
     * @param tipo Puede ser "ataque" o "defensa".
     * @return El puntaje calculado.
     */
    private int calcularPuntaje(VehiculosGuerra vehiculo, String tipo) {
        double factorVehiculo = random.nextDouble();
        double base = tipo.equals("ataque") ? vehiculo.getAtaqueBase() : vehiculo.getDefensaBase();
        double puntajeVehiculo = base * factorVehiculo;

        double factorGuerreros = random.nextDouble() * 0.5;
        int sumaAtributosGuerreros = vehiculo.getGuerreros().stream()
                .mapToInt(guerrero -> tipo.equals("ataque") ? guerrero.getFuerzaBase() : guerrero.getResistencia())
                .sum();
        double puntajeGuerreros = sumaAtributosGuerreros * factorGuerreros;

        return (int) Math.round(puntajeVehiculo + puntajeGuerreros);
    }
}