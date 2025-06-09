package com.warspringbootjava.WarSpringJava.beans;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
@Component
public class HumanosBean {
	
	private Long id;
    private String tipoGuerrero;
    private int fuerzaBase;
    private int resistencia;
    
    // Constructor sin parámetros
	public HumanosBean() {
	}
	

	public HumanosBean(String tipoGuerrero, int fuerzaBase, int resistencia) {
		super();
		this.tipoGuerrero = tipoGuerrero;
		this.fuerzaBase = fuerzaBase;
		this.resistencia = resistencia;
	}


	public HumanosBean(Long id, String tipoGuerrero, int fuerzaBase, int resistencia) {
		super();
		this.id = id;
		this.tipoGuerrero = tipoGuerrero;
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
		StringBuilder builder = new StringBuilder();
		builder.append("HumanosDTO [id=");
		builder.append(id);
		builder.append(", tipoGuerrero=");
		builder.append(tipoGuerrero);
		builder.append(", fuerzaBase=");
		builder.append(fuerzaBase);
		builder.append(", resistencia=");
		builder.append(resistencia);
		builder.append("]");
		return builder.toString();
	}
	


}
