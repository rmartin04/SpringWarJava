package com.warsrpingbootjava.WarSpringJava.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.warsrpingbootjava.WarSpringJava.excepciones.FuerzaGuerreroException;
import com.warsrpingbootjava.WarSpringJava.excepciones.FuerzaYResistenciaException;
import com.warsrpingbootjava.WarSpringJava.excepciones.ResistenciaGuerreroException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity

public  class Guerrero{
    private static final Logger logger = LoggerFactory.getLogger(Guerrero.class);

    private static int contador; 
    // Atributos que van a tener y heredar los guerreros
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "ID_GUERRERO")
    private int idGuerrero;
	@Column(name = "TIPO_GUERRERO")
    private String tipoGuerrero;
	@Column(name = "FUERZA_BASE")
    private int fuerzaBase;
	@Column(name = "RESISTENCIA")
    private int resistencia;
	
	@ManyToOne
	private VehiculosGuerra vehiculoGuerra;

    public Guerrero() {
    }

    // Constructor
    public Guerrero(String tipoGuerrero, int fuerzaBase, int resistencia)
            throws FuerzaYResistenciaException, FuerzaGuerreroException, ResistenciaGuerreroException {
        this.idGuerrero = ++contador;
        this.tipoGuerrero = tipoGuerrero;

        // Validaciones de ataque y defensa
        if (fuerzaBase + resistencia > 10) {
            throw new FuerzaYResistenciaException("La suma del fuerza y la resistencia no puede superar los 10 puntos.");
        }
        if (fuerzaBase > 10) {
            throw new FuerzaGuerreroException("El total de la fuerza no puede ser mayor a 10.");
        }
        if (resistencia > 10) {
            throw new ResistenciaGuerreroException("El total de la resistencia no puede ser mayor a 10.");
        }
        // Asignación de valores
        if (fuerzaBase < 5) {
            // Si la fuerza es menor a 5, se asignan valores por defecto
            this.fuerzaBase = 5;
            // Para que la suma no exceda 10, la resistencia se ajusta al máximo permitido
            this.resistencia = Math.min(resistencia, 10 - this.fuerzaBase);
        } else {
            // Si la fuerza es suficiente, se asignan los valores proporcionados
            this.fuerzaBase = fuerzaBase;
            this.resistencia = resistencia;
        }
    }

    // Getter & Setters
    public int getIdGuerrero() {
        return idGuerrero;
    }

    public void setIdGuerrero(int idGuerrero) {
        this.idGuerrero = idGuerrero;
    }

    public String getTipoGuerrero() {
        return tipoGuerrero;
    }

    public void setTipoGuerrero(String tipoGuerrero) {
        this.tipoGuerrero = tipoGuerrero;
    }

    public int getFuerzaBase() {
        return fuerzaBase;
    }

    public void setFuerzaBase(int fuerzaBase) {
        this.fuerzaBase = fuerzaBase;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n***Características del " + getTipoGuerrero() + "***\n");
        sb.append("Id guerrero: ").append(idGuerrero).append("\n");
        sb.append("Tipo de Guerrero:").append(tipoGuerrero).append("\n");
        sb.append("Fuerza Base: ").append(fuerzaBase).append("\n");
        sb.append("Resistencia: ").append(resistencia);
        return sb.toString();
    }

}
