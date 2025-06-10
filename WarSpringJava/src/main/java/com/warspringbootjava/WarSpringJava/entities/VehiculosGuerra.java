package com.warspringbootjava.WarSpringJava.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.warspringbootjava.WarSpringJava.excepciones.AtaqueDefensaException;
import com.warspringbootjava.WarSpringJava.excepciones.AtaqueMuyPoderosoException;
import com.warspringbootjava.WarSpringJava.excepciones.DefensaMuyPoderosaException;
import com.warspringbootjava.WarSpringJava.excepciones.EmbarcarGuerrerosException;
import com.warspringbootjava.WarSpringJava.excepciones.VidaMaximaPermitidaException;
import com.warspringbootjava.WarSpringJava.interfaces.Tripulable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity

public  class VehiculosGuerra implements Tripulable {

    private static final Logger logger = LoggerFactory.getLogger(VehiculosGuerra.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	// Atributos que van a tener los vehículos de guerra
	// Puntos de vida del vehículo, por defecto 1000
	@Min(value = 0, message = "Los puntos de vida mínimos son 0")
	@Max(value = 1000, message = "Los puntos de vida máximos son 1000")
	@Column(name = "PUNTOS_VIDA")
    private int puntosVida;
	
	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 3, max = 20)
	@Column(name = "NOMBRE_VEHICULO")
    private String nombreVehiculo;
	@NotBlank(message = "El tipo de vehiculo es obligatorio")
	@Column(name = "TIPO_VEHICULO")
    private String tipoVehiculo;
	// Atributos de ataque y defensa
	// Estos atributos no pueden ser negativos ni superar el total de 10
	@Min(value = 0, message = "El ataque mínimo es 0")
    @Max(value = 10, message = "El ataque máximo es 10")
	@Column(name = "ATAQUE_BASE")
    private int ataqueBase;
	
	@Min(value = 0, message = "La defensa mínima es 0")
	@Max(value = 10, message = "La defensa máxima es 10")
	@Column(name = "DEFENSA_BASE")
    private int defensaBase;
	
    @OneToMany(mappedBy = "vehiculoGuerra",cascade = CascadeType.ALL)	
    private List<Guerrero> guerreros = new ArrayList<>();
    // Constructor sin parámetros
    public VehiculosGuerra() {
    	
    }

    // Constructores
    public VehiculosGuerra(String nombreVehiculo, String tipoVehiculo, int ataqueBase, int defensaBase,
            List<Guerrero> guerreros)  {
      
           this.puntosVida = 1000; // Valor por defecto
           this.nombreVehiculo = nombreVehiculo;
           this.tipoVehiculo = tipoVehiculo;
           this.ataqueBase = ataqueBase;
           this.defensaBase = defensaBase;
           this.guerreros = guerreros;
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
    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
