package com.warspringbootjava.WarSpringJava.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warspringbootjava.WarSpringJava.entities.VehiculosGuerra;
import com.warspringbootjava.WarSpringJava.repositories.VehiculosGuerraRepository;

@Service
public class BatallaService {

    @Autowired
    private VehiculosGuerraRepository vehiculosGuerraRepository;

    private final Random random = new Random();

    public List<String> iniciarBatallaPorTurnos(Long vehiculoId1, Long vehiculoId2) {
        VehiculosGuerra vehiculo1 = vehiculosGuerraRepository.findById(vehiculoId1)
                .orElseThrow(() -> new IllegalArgumentException("El vehículo con ID " + vehiculoId1 + " no existe."));
        VehiculosGuerra vehiculo2 = vehiculosGuerraRepository.findById(vehiculoId2)
                .orElseThrow(() -> new IllegalArgumentException("El vehículo con ID " + vehiculoId2 + " no existe."));

        // Copiar puntos de vida para no modificar la BD
        int vida1 = vehiculo1.getPuntosVida();
        int vida2 = vehiculo2.getPuntosVida();

        List<String> logBatalla = new ArrayList<>();
        int turno = 1;
        int maxTurnos = 20; // evitar loop infinito

        while (vida1 > 0 && vida2 > 0 && turno <= maxTurnos) {
            logBatalla.add("Turno " + turno + ":");

            // Vehículo 1 ataca a Vehículo 2
            int ataque1 = calcularPuntaje(vehiculo1, "ataque");
            int defensa2 = calcularPuntaje(vehiculo2, "defensa");
            int dano1a2 = Math.max(0, ataque1 - defensa2);
            vida2 -= dano1a2;
            vida2 = Math.max(vida2, 0);
            logBatalla.add(vehiculo1.getNombreVehiculo() + " ataca con " + ataque1 + " de ataque.");
            logBatalla.add(vehiculo2.getNombreVehiculo() + " defiende con " + defensa2 + ".");
            logBatalla.add(vehiculo2.getNombreVehiculo() + " recibe " + dano1a2 + " puntos de daño, vida restante: " + vida2);

            if (vida2 <= 0) {
                logBatalla.add(vehiculo2.getNombreVehiculo() + " ha sido destruido.");
                break;
            }

            // Vehículo 2 ataca a Vehículo 1
            int ataque2 = calcularPuntaje(vehiculo2, "ataque");
            int defensa1 = calcularPuntaje(vehiculo1, "defensa");
            int dano2a1 = Math.max(0, ataque2 - defensa1);
            vida1 -= dano2a1;
            vida1 = Math.max(vida1, 0);
            logBatalla.add(vehiculo2.getNombreVehiculo() + " ataca con " + ataque2 + " de ataque.");
            logBatalla.add(vehiculo1.getNombreVehiculo() + " defiende con " + defensa1 + ".");
            logBatalla.add(vehiculo1.getNombreVehiculo() + " recibe " + dano2a1 + " puntos de daño, vida restante: " + vida1);

            if (vida1 <= 0) {
                logBatalla.add(vehiculo1.getNombreVehiculo() + " ha sido destruido.");
                break;
            }

            turno++;
            logBatalla.add("-------------------------");
        }

        // Resultado final
        if (vida1 > vida2) {
            logBatalla.add("Resultado final: " + vehiculo1.getNombreVehiculo() + " ha ganado la batalla.");
        } else if (vida2 > vida1) {
            logBatalla.add("Resultado final: " + vehiculo2.getNombreVehiculo() + " ha ganado la batalla.");
        } else {
            logBatalla.add("Resultado final: La batalla ha terminado en empate.");
        }

        return logBatalla;
    }

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