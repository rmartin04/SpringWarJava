package com.warspringbootjava.WarSpringJava.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.warspringbootjava.WarSpringJava.excepciones.FuerzaGuerreroException;
import com.warspringbootjava.WarSpringJava.excepciones.FuerzaYResistenciaException;
import com.warspringbootjava.WarSpringJava.excepciones.ResistenciaGuerreroException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity

public  class Guerrero{
    private static final Logger logger = LoggerFactory.getLogger(Guerrero.class);

    // Atributos que van a tener y heredar los guerreros
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NOMBRE")
    private String nombre;
	@Column(name = "TIPO_GUERRERO")
    private String tipo;
	@Column(name = "FUERZA_BASE")
    private int fuerzaBase;
	@Column(name = "RESISTENCIA")
    private int resistencia;
	
	@ManyToOne
	@JoinColumn(name = "vehiculo_guerra_id")
	private VehiculosGuerra vehiculoGuerra;

    public Guerrero() {
    }

    // Constructor
    public Guerrero(String nombre, String tipoGuerrero, int fuerzaBase, int resistencia)
            throws FuerzaYResistenciaException, FuerzaGuerreroException, ResistenciaGuerreroException {
    	this.nombre = nombre;
        this.tipo = tipoGuerrero;
        this.fuerzaBase = fuerzaBase;
        this.resistencia = resistencia;
    }

    // Getter & Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        sb.append("\n***Características del " + getTipo() + "***\n");
        sb.append("Id guerrero: ").append(id).append("\n");
        sb.append("Tipo de Guerrero:").append(tipo).append("\n");
        sb.append("Fuerza Base: ").append(fuerzaBase).append("\n");
        sb.append("Resistencia: ").append(resistencia);
        return sb.toString();
    }


}
