package com.warsrpingbootjava.WarSpringJava.beans;

import java.util.List;

import com.warsrpingbootjava.WarSpringJava.clases.Guerrero;
import jakarta.persistence.Column;

public class NaveDestructoraDTO {
	
	 	private Long idNaveDestructora;
	    private int puntosVida;
	    private String nombreVehiculo;
	    private String tipoVehiculo;	   
	    private int ataqueBase;	 
	    private int defensaBase;	  
	    private List<Guerrero> guerreros;


	    public NaveDestructoraDTO() {}


		public NaveDestructoraDTO(int puntosVida, String nombreVehiculo, String tipoVehiculo, int ataqueBase,
				int defensaBase, List<Guerrero> guerreros) {
			super();
			this.puntosVida = puntosVida;
			this.nombreVehiculo = nombreVehiculo;
			this.tipoVehiculo = tipoVehiculo;
			this.ataqueBase = ataqueBase;
			this.defensaBase = defensaBase;
			this.guerreros = guerreros;
		}


		public NaveDestructoraDTO(Long idNaveDestructora, int puntosVida, String nombreVehiculo, String tipoVehiculo,
				int ataqueBase, int defensaBase, List<Guerrero> guerreros) {
			super();
			this.idNaveDestructora = idNaveDestructora;
			this.puntosVida = puntosVida;
			this.nombreVehiculo = nombreVehiculo;
			this.tipoVehiculo = tipoVehiculo;
			this.ataqueBase = ataqueBase;
			this.defensaBase = defensaBase;
			this.guerreros = guerreros;
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
			StringBuilder builder = new StringBuilder();
			builder.append("NaveDestructoraDTO [idNaveDestructora=");
			builder.append(idNaveDestructora);
			builder.append(", puntosVida=");
			builder.append(puntosVida);
			builder.append(", nombreVehiculo=");
			builder.append(nombreVehiculo);
			builder.append(", tipoVehiculo=");
			builder.append(tipoVehiculo);
			builder.append(", ataqueBase=");
			builder.append(ataqueBase);
			builder.append(", defensaBase=");
			builder.append(defensaBase);
			builder.append(", guerreros=");
			builder.append(guerreros);
			builder.append("]");
			return builder.toString();
		}
	    
	    

}
