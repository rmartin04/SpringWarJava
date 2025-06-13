package com.warspringbootjava.WarSpringJava.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.warspringbootjava.WarSpringJava.interfaces.Tripulable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "VEHICULO_GUERRA")
public class VehiculoGuerra implements Tripulable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @NotNull(message = "La vida es obligatoria")
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

    @NotNull(message = "La fuerza es obligatoria")
    @Min(value = 0, message = "El ataque mínimo es 0")
    @Max(value = 10, message = "El ataque máximo es 10")
    @Column(name = "ATAQUE_BASE")
    private Integer ataqueBase;

    @NotNull(message = "La defensa es obligatoria")
    @Min(value = 0, message = "La defensa mínima es 0")
    @Max(value = 10, message = "La defensa máxima es 10")
    @Column(name = "DEFENSA_BASE")
    private Integer defensaBase;

    @OneToMany(mappedBy = "vehiculoGuerra", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Guerrero> guerreros = new ArrayList<>();

    // NUEVO ATRIBUTO transitorio para nombres concatenados de guerreros
    @Transient
    public String getGuerrerosNombres() {
        return guerreros.stream()
            .map(Guerrero::getNombre)
            .reduce((a, b) -> a + ", " + b)
            .orElse("Sin guerreros");
    }
    
    @Override
    public int atacar() {
        Random random = new Random();

        double factorVehiculo = random.nextDouble();
        double ataqueVehiculo = ataqueBase * factorVehiculo;

        double factorGuerreros = random.nextDouble() * 0.5;
        int sumaAtaqueGuerreros = guerreros.stream().mapToInt(Guerrero::getFuerzaBase).sum();
        double ataqueGuerreros = sumaAtaqueGuerreros * factorGuerreros;

        return (int) Math.round(ataqueVehiculo + ataqueGuerreros);
    }

    @Override
    public int defender(int ataqueEntrante) {
        Random random = new Random();

        double factorVehiculo = random.nextDouble();
        double defensaVehiculo = defensaBase * factorVehiculo;

        double factorGuerreros = random.nextDouble() * 0.5;
        int sumaResistenciaGuerreros = guerreros.stream().mapToInt(Guerrero::getResistencia).sum();
        double defensaGuerreros = sumaResistenciaGuerreros * factorGuerreros;

        return (int) Math.round(defensaVehiculo + defensaGuerreros);
    }

    public VehiculoGuerra() {
    }

    public VehiculoGuerra(int puntosVida, String nombreVehiculo, String tipoVehiculo, Integer ataqueBase, Integer defensaBase,
            List<Guerrero> guerreros) {
    	this.puntosVida = puntosVida; // Asignar puntos de vida por defecto
        this.nombreVehiculo = nombreVehiculo;
        this.tipoVehiculo = tipoVehiculo;
        this.ataqueBase = ataqueBase;
        this.defensaBase = defensaBase;
        this.guerreros = guerreros;
    }

    // Getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getAtaqueBase() {
        return ataqueBase;
    }

    public void setAtaqueBase(Integer ataqueBase) {
        this.ataqueBase = ataqueBase;
    }

    public Integer getDefensaBase() {
        return defensaBase;
    }

    public void setDefensaBase(Integer defensaBase) {
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


    // Métodos de la clase (toString, atacar, defender) mantienen igual

    @Override
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
    
    public void addGuerrero(Guerrero guerrero) {
        guerreros.add(guerrero);
        guerrero.setVehiculoGuerra(this); // vincula el otro lado
    }

    public void removeGuerrero(Guerrero guerrero) {
        guerreros.remove(guerrero);
        guerrero.setVehiculoGuerra(null); // desvincula el otro lado
    }


}
