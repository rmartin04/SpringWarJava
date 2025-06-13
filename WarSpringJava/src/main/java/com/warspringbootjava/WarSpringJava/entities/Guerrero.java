 package com.warspringbootjava.WarSpringJava.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public  class Guerrero{

    // Atributos que van a tener y heredar los guerreros
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 3, max = 20)
	@Column(name = "NOMBRE")
    private String nombre;
	
	@NotBlank(message = "El tipo de guerrero es obligatorio")
	@Column(name = "TIPO_GUERRERO")
    private String tipo;
	
	@NotNull(message = "La fuerza es obligatoria")
    @Min(value = 0, message = "La fuerza mínima es 0")
    @Max(value = 10, message = "La fuerza máxima es 10")
	@Column(name = "FUERZA_BASE")
    private Integer fuerzaBase;
	
	@NotNull(message = "La resistencia es obligatoria")
    @Min(value = 0, message = "La resistencia mínima es 0")
    @Max(value = 10, message = "La resistencia máxima es 10")
	@Column(name = "RESISTENCIA")
    private Integer resistencia;
	
	@ManyToOne
	@JoinColumn(name = "vehiculo_guerra_id")
	@JsonBackReference
	private VehiculoGuerra vehiculoGuerra;

    public Guerrero() {
    }

    // Constructor
    public Guerrero(String nombre, String tipoGuerrero, Integer fuerzaBase, Integer resistencia) {
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

    public Integer getFuerzaBase() {
        return fuerzaBase;
    }

    public void setFuerzaBase(Integer fuerzaBase) {
        this.fuerzaBase = fuerzaBase;
    }

    public Integer getResistencia() {
        return resistencia;
    }

    public void setResistencia(Integer resistencia) {
        this.resistencia = resistencia;
    }
    
    public VehiculoGuerra getVehiculoGuerra() {
        return vehiculoGuerra;
    }

    public void setVehiculoGuerra(VehiculoGuerra vehiculoGuerra) {
        this.vehiculoGuerra = vehiculoGuerra;
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
