package com.warsrpingbootjava.WarSpringJava.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.warsrpingbootjava.WarSpringJava.excepciones.AtaqueDefensaException;
import com.warsrpingbootjava.WarSpringJava.excepciones.AtaqueMuyPoderosoException;
import com.warsrpingbootjava.WarSpringJava.excepciones.DefensaMuyPoderosaException;
import com.warsrpingbootjava.WarSpringJava.excepciones.EmbarcarGuerrerosException;
import com.warsrpingbootjava.WarSpringJava.excepciones.VidaMaximaPermitidaException;
import com.warsrpingbootjava.WarSpringJava.interfaces.Tripulable;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;


@Entity

public  class VehiculosGuerra implements Tripulable {

    private static final Logger logger = LoggerFactory.getLogger(VehiculosGuerra.class);

    // Atributos de la clase Vehiculos de guerra
    private int puntosVida;
    private String nombreVehiculo;
    private String tipoVehiculo;
    private int ataqueBase;
    private int defensaBase;
    private List<Guerrero> guerreros;
    // Constructor sin parámetros
    public VehiculosGuerra() {
    	
    }

    // Constructores
    public VehiculosGuerra(String nombreVehiculo, String tipoVehiculo, int ataqueBase, int defensaBase,
            List<Guerrero> guerreros) throws VidaMaximaPermitidaException, AtaqueDefensaException,
            AtaqueMuyPoderosoException, DefensaMuyPoderosaException, EmbarcarGuerrerosException {
        if (puntosVida > 1000) {
            throw new VidaMaximaPermitidaException("No se puede tener más de 1000 puntos de vida. ¡Deja de hacer trampa tronco!");
        } else {
            this.puntosVida = 1000;
        }

        this.nombreVehiculo = nombreVehiculo;
        this.tipoVehiculo = tipoVehiculo;

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
            this.ataqueBase = 5;
            this.defensaBase = 5;
        } else {
            this.ataqueBase = ataqueBase;
            this.defensaBase = defensaBase;
        }

        // Validación y asignación de la lista de guerreros
        if (guerreros == null) {
            this.guerreros = new ArrayList<>();
        } else {
            if (guerreros.size() > 10) {
                throw new EmbarcarGuerrerosException("No se pueden embarcar más de 10 guerreros. ¡TRAMPOSO!");
            } else {
                // Se asigna una copia para evitar modificaciones externas
                this.guerreros = new ArrayList<>(guerreros);
            }
        }
    }

    // Getters & Setters
    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    public String getNombreVehiculo() {
        return nombreVehiculo;
    }

    public void setNombreVehiculo(String nombreVehiculo) {
        this.nombreVehiculo = nombreVehiculo;
    }

    public int getAtaqueBase() {
        return ataqueBase;
    }

    public void setAtaqueBase(int ataqueBase) {
        this.ataqueBase = ataqueBase;
    }

    public int getDefensaBase() {
        return defensaBase;
    }

    public void setDefensaBase(int defensaBase) {
        this.defensaBase = defensaBase;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public List<Guerrero> getGuerreros() {
        return guerreros;
    }

    public void setGuerreros(List<Guerrero> guerreros) {
        this.guerreros = guerreros;
    }

    // Métodos del vehiculo de guerra
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n***Características del vehículo " + nombreVehiculo + "***\n");
        sb.append("\tVida del " + nombreVehiculo + ": ").append(getPuntosVida()).append("\n");
        sb.append("\tNombre: ").append(nombreVehiculo).append("\n");
        sb.append("\tTipo de Vehículo: ").append(tipoVehiculo).append("\n");
        sb.append("\tAtaque: ").append(ataqueBase).append("\n");
        sb.append("\tDefensa: ").append(defensaBase).append("\n");
        sb.append("\tGuerreros a bordo: ").append(guerreros.size());
        return sb.toString();
    }

    @Override
    public int atacar() {
        Random random = new Random();

        // Parte 1: ataque del vehículo con random entre 0 y 1
        double factorVehiculo = random.nextDouble(); // entre 0.0 y 1.0
        double ataqueVehiculo = ataqueBase * factorVehiculo;

        // Parte 2: suma del ataque de todos los guerreros * random(0 - 0.5)
        double factorGuerreros = random.nextDouble() * 0.5; // entre 0.0 y 0.5
        int sumaAtaqueGuerreros = 0;
        for (Guerrero guerrero : guerreros) {
            sumaAtaqueGuerreros += guerrero.getFuerzaBase();
        }
        double ataqueGuerreros = sumaAtaqueGuerreros * factorGuerreros;

        // Suma total del ataque
        return (int) Math.round(ataqueVehiculo + ataqueGuerreros);
    }

    @Override
    public int defender(int ataqueEntrante) {
        Random random = new Random();

        // Parte 1: defensa del vehículo con random entre 0 y 1
        double factorVehiculo = random.nextDouble(); // 0.0 - 1.0
        double defensaVehiculo = defensaBase * factorVehiculo;

        // Parte 2: defensa de los guerreros con random entre 0 y 0.5
        double factorGuerreros = random.nextDouble() * 0.5; // 0.0 - 0.5
        int sumaResistenciaGuerreros = 0;
        for (Guerrero guerrero : guerreros) {
            sumaResistenciaGuerreros += guerrero.getResistencia();
        }
        double defensaGuerreros = sumaResistenciaGuerreros * factorGuerreros;

        // Defensa total
        int defensaTotal = (int) Math.round(defensaVehiculo + defensaGuerreros);

        // Puedes usar este valor para restar vida, o retornarlo directamente
        return defensaTotal;
    }
}
