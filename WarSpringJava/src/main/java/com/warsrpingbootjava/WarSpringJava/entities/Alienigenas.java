package com.warsrpingbootjava.WarSpringJava.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ALIENIGENAS")
public class Alienigenas extends Guerrero {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ID")
		private Long id;
		
        @Column(name = "TIPO_GUERRERO")
	    private String tipoGuerrero;
        @Column(name = "FUERZA_BASE")
	    private int fuerzaBase;
        @Column(name = "RESISTENCIA")
	    private int resistencia;

            // Constructor sin parámetros
	public Alienigenas() {
		super();
	}
	// Constructor
	 public Alienigenas(String tipoGuerrero, int fuerzaBase, int resistencia)
	            throws FuerzaYResistenciaException, FuerzaGuerreroException, ResistenciaGuerreroException {
	        super(tipoGuerrero, fuerzaBase, resistencia);
	    }
	 // Getter & Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
		return "Alienigenas [id=" + id + ", tipoGuerrero=" + tipoGuerrero + ", fuerzaBase=" + fuerzaBase
				+ ", resistencia=" + resistencia + "]";
	}
	 

	
	

}
