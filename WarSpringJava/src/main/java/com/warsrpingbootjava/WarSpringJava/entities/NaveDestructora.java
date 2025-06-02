package com.warsrpingbootjava.WarSpringJava.entities;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.javawarweb.clases.Guerrero;
import es.javawarweb.clases.VehiculosGuerra;
import es.javawarweb.excepciones.AtaqueDefensaException;
import es.javawarweb.excepciones.AtaqueMuyPoderosoException;
import es.javawarweb.excepciones.DefensaMuyPoderosaException;
import es.javawarweb.excepciones.EmbarcarGuerrerosDiferentesException;
import es.javawarweb.excepciones.EmbarcarGuerrerosException;
import es.javawarweb.excepciones.VidaMaximaPermitidaException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="TB_NAVE_DESTRUCTORA")
public class NaveDestructora extends VehiculosGuerra {
    private static final Logger logger = LoggerFactory.getLogger(NaveDestructora.class); 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_NAVE_DESTRUCTORA")
    private Long idNaveDestructora;
    @Column(name = "PUNTOS_VIDA")
    private int puntosVida;
    @Column(name = "NOMBRE_VEHICULO")
    private String nombreVehiculo;
    @Column(name = "TIPO_VEHICULO")
    private String tipoVehiculo;
    @Column(name = "ATAQUE_BASE")
    private int ataqueBase;
    @Column(name = "DEFENSA_BASE")
    private int defensaBase;
    @Column(name = "LISTA_GUERREROS")
    private List<Guerrero> guerreros;
    // Constructor sin parámetros
        public NaveDestructora() {}

    // Constructores
    public NaveDestructora(String nombreVehiculo, String tipoVehiculo, int ataqueBase, int defensaBase,
            List<Guerrero> guerreros) throws VidaMaximaPermitidaException, AtaqueDefensaException, AtaqueMuyPoderosoException,
            DefensaMuyPoderosaException, EmbarcarGuerrerosException {
        super(nombreVehiculo, tipoVehiculo, ataqueBase, defensaBase, guerreros);
    }

    // Métodos
    public void embarcarGuerreros() throws EmbarcarGuerrerosException, EmbarcarGuerrerosDiferentesException {
        for (Guerrero guerrero : getGuerreros()) {
            if ("Bicho".equalsIgnoreCase(guerrero.getTipoGuerrero())) {
                logger.info("Se ha embarcado al Guerrero " + guerrero.getTipoGuerrero() + " correctamente.");
                logger.info(guerrero.toString());
            } else {
                throw new EmbarcarGuerrerosDiferentesException("No se pueden embarcar otro tipo de guerreros en el " + this.getNombreVehiculo());
            }
        }
    }

	public Long getIdNaveDestructora() {
		return idNaveDestructora;
	}

	public void setIdNaveDestructora(Long idNaveDestructora) {
		this.idNaveDestructora = idNaveDestructora;
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

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
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

	public List<Guerrero> getGuerreros() {
		return guerreros;
	}

	public void setGuerreros(List<Guerrero> guerreros) {
		this.guerreros = guerreros;
	}

	@Override
	public String toString() {
		return "NaveDestructora [idNaveDestructora=" + idNaveDestructora + ", puntosVida=" + puntosVida
				+ ", nombreVehiculo=" + nombreVehiculo + ", tipoVehiculo=" + tipoVehiculo + ", ataqueBase=" + ataqueBase
				+ ", defensaBase=" + defensaBase + ", guerreros=" + guerreros + "]";
	}
    
}

