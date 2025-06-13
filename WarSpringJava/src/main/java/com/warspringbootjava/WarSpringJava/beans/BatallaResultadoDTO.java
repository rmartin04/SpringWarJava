package com.warspringbootjava.WarSpringJava.beans;

import java.util.List;

public class BatallaResultadoDTO {
	
    private List<String> log;
    private String ganador;
    private Long idGanador;
    private int turnosTotales;
    
	public BatallaResultadoDTO() {
		super();
	}

	public BatallaResultadoDTO(List<String> log, String ganador, Long idGanador, int turnosTotales) {
		super();
		this.log = log;
		this.ganador = ganador;
		this.idGanador = idGanador;
		this.turnosTotales = turnosTotales;
	}

	public List<String> getLog() {
		return log;
	}

	public void setLog(List<String> log) {
		this.log = log;
	}

	public String getGanador() {
		return ganador;
	}

	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	public Long getIdGanador() {
		return idGanador;
	}

	public void setIdGanador(Long idGanador) {
		this.idGanador = idGanador;
	}

	public int getTurnosTotales() {
		return turnosTotales;
	}

	public void setTurnosTotales(int turnosTotales) {
		this.turnosTotales = turnosTotales;
	}

	
    
}
