package com.warspringbootjava.WarSpringJava.beans;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.warspringbootjava.WarSpringJava.entities.Guerrero;
import com.warspringbootjava.WarSpringJava.entities.VehiculoGuerra;
import com.warspringbootjava.WarSpringJava.excepciones.EmbarcarGuerrerosDiferentesException;
import com.warspringbootjava.WarSpringJava.excepciones.EmbarcarGuerrerosException;
@Component
public class NaveDestructoraBean extends VehiculoGuerra {
	
	private static final Logger logger = LoggerFactory.getLogger(VehiculoGuerra.class);
	
	
	 	private Long idNaveDestructora;
	    private int puntosVida;
	    private String nombreVehiculo;
	    private String tipoVehiculo;	   
	    private int ataqueBase;	 
	    private int defensaBase;	  
	    private List<Guerrero> guerreros;


	    public NaveDestructoraBean() {}
	    
	    


		public NaveDestructoraBean(String nombreVehiculo, String tipoVehiculo, int ataqueBase, int defensaBase,
				List<Guerrero> guerreros) {
			super();
			this.nombreVehiculo = nombreVehiculo;
			this.tipoVehiculo = tipoVehiculo;
			this.ataqueBase = ataqueBase;
			this.defensaBase = defensaBase;
			this.guerreros = guerreros;
		}




		public NaveDestructoraBean(int puntosVida, String nombreVehiculo, String tipoVehiculo, int ataqueBase,
				int defensaBase, List<Guerrero> guerreros) {
			super();
			this.puntosVida = puntosVida;
			this.nombreVehiculo = nombreVehiculo;
			this.tipoVehiculo = tipoVehiculo;
			this.ataqueBase = ataqueBase;
			this.defensaBase = defensaBase;
			this.guerreros = guerreros;
		}


		public NaveDestructoraBean(Long idNaveDestructora, int puntosVida, String nombreVehiculo, String tipoVehiculo,
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
	    
	    public void embarcarGuerreros() throws EmbarcarGuerrerosException, EmbarcarGuerrerosDiferentesException {
	        for (Guerrero guerrero : getGuerreros()) {
	            if ("Bicho".equalsIgnoreCase(guerrero.getTipo())) {
	                logger.info("Se ha embarcado al Guerrero " + guerrero.getTipo() + " correctamente.");
	                logger.info(guerrero.toString());
	            } else {
	                throw new EmbarcarGuerrerosDiferentesException("No se pueden embarcar otro tipo de guerreros en el " + this.getNombreVehiculo());
	            }
	        }
	    }
	    

}
