package com.warspringbootjava.WarSpringJava.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.warspringbootjava.WarSpringJava.beans.BatallaResultadoDTO;
import com.warspringbootjava.WarSpringJava.entities.VehiculoGuerra;
import com.warspringbootjava.WarSpringJava.repositories.VehiculosGuerraRepository;

@Service
public class BatallaService {

    private VehiculosGuerraRepository vehiculosGuerraRepository;
    public BatallaService(VehiculosGuerraRepository vehiculosGuerraRepository) {
		this.vehiculosGuerraRepository = vehiculosGuerraRepository;
	}

    private final Random random = new Random();

    public BatallaResultadoDTO iniciarBatallaPorTurnos(Long vehiculoId1, Long vehiculoId2) {
        VehiculoGuerra vehiculo1 = vehiculosGuerraRepository.findById(vehiculoId1)
                .orElseThrow(() -> new IllegalArgumentException("El vehículo con ID " + vehiculoId1 + " no existe."));
        VehiculoGuerra vehiculo2 = vehiculosGuerraRepository.findById(vehiculoId2)
                .orElseThrow(() -> new IllegalArgumentException("El vehículo con ID " + vehiculoId2 + " no existe."));

        if (vehiculo1.getGuerreros() == null || vehiculo1.getGuerreros().isEmpty()) {
            throw new IllegalArgumentException("El vehículo " + vehiculo1.getNombreVehiculo() + " no tiene guerreros embarcados.");
        }
        if (vehiculo2.getGuerreros() == null || vehiculo2.getGuerreros().isEmpty()) {
            throw new IllegalArgumentException("El vehículo " + vehiculo2.getNombreVehiculo() + " no tiene guerreros embarcados.");
        }

        int vida1 = vehiculo1.getPuntosVida();
        int vida2 = vehiculo2.getPuntosVida();

        List<String> logBatalla = new ArrayList<>();
        int turno = 1;
        int maxTurnos = 20;

        while (vida1 > 0 && vida2 > 0 && turno <= maxTurnos) {
            logBatalla.add("=== TURNO " + turno + " ===");

            // Ataque de vehículo 1 a 2
            int ataque1 = calcularPuntaje(vehiculo1, "ataque");
            int defensa2 = calcularPuntaje(vehiculo2, "defensa");
            int dano1a2 = Math.max(0, ataque1 - defensa2);
            vida2 -= dano1a2;
            vida2 = Math.max(vida2, 0);

            logBatalla.add("[ATAQUE] " + vehiculo1.getNombreVehiculo() + " ataca con " + ataque1);
            logBatalla.add("[DEFENSA] " + vehiculo2.getNombreVehiculo() + " defiende con " + defensa2);
            logBatalla.add("[DAÑO] " + vehiculo2.getNombreVehiculo() + " pierde " + dano1a2 + " puntos, vida restante: " + vida2);

            if (vida2 <= 0) {
                logBatalla.add("[DESTRUCCIÓN] " + vehiculo2.getNombreVehiculo() + " ha sido destruido.");
                break;
            }

            // Ataque de vehículo 2 a 1
            int ataque2 = calcularPuntaje(vehiculo2, "ataque");
            int defensa1 = calcularPuntaje(vehiculo1, "defensa");
            int dano2a1 = Math.max(0, ataque2 - defensa1);
            vida1 -= dano2a1;
            vida1 = Math.max(vida1, 0);

            logBatalla.add("[ATAQUE] " + vehiculo2.getNombreVehiculo() + " ataca con " + ataque2);
            logBatalla.add("[DEFENSA] " + vehiculo1.getNombreVehiculo() + " defiende con " + defensa1);
            logBatalla.add("[DAÑO] " + vehiculo1.getNombreVehiculo() + " pierde " + dano2a1 + " puntos, vida restante: " + vida1);

            if (vida1 <= 0) {
                logBatalla.add("[DESTRUCCIÓN] " + vehiculo1.getNombreVehiculo() + " ha sido destruido.");
                break;
            }

            turno++;
            logBatalla.add("");
        }

        String ganador;
        Long idGanador;

        if (vida1 > vida2) {
            ganador = vehiculo1.getNombreVehiculo();
            idGanador = vehiculo1.getId();
        } else if (vida2 > vida1) {
            ganador = vehiculo2.getNombreVehiculo();
            idGanador = vehiculo2.getId();
        } else {
            ganador = "Empate";
            idGanador = null;
        }

        logBatalla.add("=== RESULTADO FINAL ===");
        logBatalla.add(ganador.equals("Empate") ? "[EMPATE] La batalla terminó sin vencedor." : "[VICTORIA] " + ganador + " ha ganado la batalla.");

        return new BatallaResultadoDTO(logBatalla, ganador, idGanador, turno);
    }

    private int calcularPuntaje(VehiculoGuerra vehiculo, String tipo) {
        double factorVehiculo = random.nextDouble()*2;
        double base = tipo.equals("ataque") ? vehiculo.getAtaqueBase() : vehiculo.getDefensaBase();
        double puntajeVehiculo = base * factorVehiculo;

        double factorGuerreros = random.nextDouble() * 5;
        int sumaAtributosGuerreros = vehiculo.getGuerreros().stream()
                .mapToInt(guerrero -> tipo.equals("ataque") ? guerrero.getFuerzaBase() : guerrero.getResistencia())
                .sum();
        double puntajeGuerreros = sumaAtributosGuerreros * factorGuerreros;

        return (int) Math.round(puntajeVehiculo + puntajeGuerreros);
    }
}