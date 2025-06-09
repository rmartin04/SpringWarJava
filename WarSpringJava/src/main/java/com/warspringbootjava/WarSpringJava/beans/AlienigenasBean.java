package com.warspringbootjava.WarSpringJava.beans;

import org.springframework.stereotype.Component;

import com.warspringbootjava.WarSpringJava.entities.Guerrero;


@Component
public class AlienigenasBean extends Guerrero{
	
	private Long id;
    private String tipoGuerrero;
    private int fuerzaBase;
    private int resistencia;
    
    // Constructor sin parámetros
        public AlienigenasBean() {
        }

		public AlienigenasBean(String tipoGuerrero, int fuerzaBase, int resistencia) {
			super();
			this.tipoGuerrero = tipoGuerrero;
			this.fuerzaBase = fuerzaBase;
			this.resistencia = resistencia;
		}

		public AlienigenasBean(Long id, String tipoGuerrero, int fuerzaBase, int resistencia) {
			super();
			this.id = id;
			this.tipoGuerrero = tipoGuerrero;
			this.fuerzaBase = fuerzaBase;
			this.resistencia = resistencia;
		}

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
			builder.append("AlienigenasDTO [id=");
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