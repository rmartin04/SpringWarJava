package com.warsrpingbootjava.WarSpringJava.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.warsrpingbootjava.WarSpringJava.clases.Guerrero;
import com.warsrpingbootjava.WarSpringJava.entities.Alienigenas;
import com.warsrpingbootjava.WarSpringJava.entities.Humanos;
import com.warsrpingbootjava.WarSpringJava.entities.NaveDestructora;
import com.warsrpingbootjava.WarSpringJava.entities.Tanque;
import com.warsrpingbootjava.WarSpringJava.excepciones.FuerzaGuerreroException;
import com.warsrpingbootjava.WarSpringJava.excepciones.FuerzaYResistenciaException;
import com.warsrpingbootjava.WarSpringJava.excepciones.ResistenciaGuerreroException;
import com.warsrpingbootjava.WarSpringJava.interfaces.CheckedSupplier2.CheckedSupplier;




@Service
public class JavaWarService {
	
	   private static final Logger logger = LoggerFactory.getLogger(JavaWarService.class);

	 

	    // Método ejecutar
	    private void ejecutarLucha() {
	        try {
	            simularLucha();
	        } catch (Exception e) {
	            logger.error("Ha habido un error durante la guerra: ", e.getMessage());
	            e.printStackTrace();
	        }
	    }


	    private List<Guerrero> crearGuerreros(CheckedSupplier<Guerrero> constructor, int cantidad)
	            throws Exception {
	        List<Guerrero> guerreros = new ArrayList<>();
	        for (int i = 0; i < cantidad; i++) {
	            guerreros.add(constructor.get());
	        }
	        return guerreros;
	    } 

	    // Método que crea los humanos
	    private List<Guerrero> crearHumanos()
	            throws FuerzaYResistenciaException, FuerzaGuerreroException, ResistenciaGuerreroException, Exception {
	        return crearGuerreros(() -> new Humanos("Soldado", 6, 4), 10);
	    }

	    // Método que crea los alienigenas
	    private List<Guerrero> crearAlienigenas()
	            throws FuerzaYResistenciaException, FuerzaGuerreroException, ResistenciaGuerreroException, Exception {
	        return crearGuerreros(() -> new Alienigenas("Bicho", 6, 4), 10);
	    }

	    // Método que crea el tanque
	    private Tanque crearTanque()
	            throws FuerzaYResistenciaException, FuerzaGuerreroException, ResistenciaGuerreroException, Exception {
	        List<Guerrero> listadoHumanos = crearHumanos();
	        Tanque tanque = new Tanque("el tigre real", "T-34", 6, 3, listadoHumanos);
	        logger.info(tanque.toString());
	        return tanque;
	    }

	    // Método que crea la nave
	    private NaveDestructora crearNave()
	            throws FuerzaYResistenciaException, FuerzaGuerreroException, ResistenciaGuerreroException, Exception {
	        List<Guerrero> listadoAlienigenas = crearAlienigenas();
	        NaveDestructora nave = new NaveDestructora("Nostromo", "U.S.C.S.S", 6, 4, listadoAlienigenas);
	        logger.info(nave.toString());
	        return nave;
	    }

	    public void simularLucha() {
	        Tanque tanque = null;
	        NaveDestructora nave = null;
	        try {
	            tanque = crearTanque();
	            tanque.embarcarGuerreros();
	            nave = crearNave();
	            nave.embarcarGuerreros();
	        } catch (Exception e) {
	            logger.error("Error al crear los vehículos: ", e.getMessage());
	            e.printStackTrace();
	            return; // Si falla la creación, abortamos la simulación.
	        }
	    
	        logger.info("\n\tLa batalla ha comenzado entre el " + tanque.getNombreVehiculo() + " y la " + nave.getNombreVehiculo() + "!");
	        
	        while (tanque.getPuntosVida() > 0 && nave.getPuntosVida() > 0) {
	            // ---- Turno 1: El tanque ataca a la nave ----
	            int ataqueTanque = tanque.atacar();
	            int defensaNave = nave.defender(ataqueTanque);
	            // Se calcula el daño real que supera la defensa
	            int danioReal = Math.max(0, ataqueTanque - defensaNave);
	            
	            logger.info("\n***Turno " + tanque.getNombreVehiculo() + " Ataca***");
	            logger.info("Ataque del tanque: " + ataqueTanque 
	                    + " | Defensa de la nave: " + defensaNave 
	                    + " | Daño real: " + danioReal);
	            
	            // Aplicamos el daño: primero se reduce la defensa de la nave
	            int nuevaDefensaNave = nave.getDefensaBase() - danioReal;
	            if (nuevaDefensaNave >= 0) {
	                nave.setDefensaBase(nuevaDefensaNave);
	                logger.info("La defensa de la nave se reduce a: " + nuevaDefensaNave);
	            } else {
	                // Si el daño excede la defensa, se agota la defensa y el resto se resta a los puntos de vida
	                nave.setDefensaBase(0);
	                int danioAPuntosVida = -nuevaDefensaNave; // valor positivo
	                nave.setPuntosVida(nave.getPuntosVida() - danioAPuntosVida);
	                logger.info("La defensa de la nave se ha agotado. Daño a vida: " + danioAPuntosVida 
	                        + ". Puntos de vida restantes: " + nave.getPuntosVida());
	            }
	            
	            // Verificamos si la nave ha sido destruida
	            if (nave.getPuntosVida() <= 0) {
	                logger.info("¡La " + nave.getNombreVehiculo() + " ha sido destruida! El tanque ha ganado la batalla.");
	                break;
	            }
	            
	            // ---- Turno 2: La nave ataca al tanque ----
	            int ataqueNave = nave.atacar();
	            int defensaTanque = tanque.defender(ataqueNave);
	            int danioRealNave = Math.max(0, ataqueNave - defensaTanque);
	            
	            logger.info("\n***Turno " + nave.getNombreVehiculo() + " Ataca***");
	            logger.info("Ataque de la nave: " + ataqueNave 
	                    + " | Defensa del tanque: " + defensaTanque 
	                    + " | Daño real: " + danioRealNave);
	            
	            int nuevaDefensaTanque = tanque.getDefensaBase() - danioRealNave;
	            if (nuevaDefensaTanque >= 0) {
	                tanque.setDefensaBase(nuevaDefensaTanque);
	                logger.info("La defensa del tanque se reduce a: " + nuevaDefensaTanque);
	            } else {
	                tanque.setDefensaBase(0);
	                int danioAPuntosVidaTanque = -nuevaDefensaTanque;
	                tanque.setPuntosVida(tanque.getPuntosVida() - danioAPuntosVidaTanque);
	                logger.info("La defensa del tanque se ha agotado. Daño a vida: " + danioAPuntosVidaTanque 
	                        + ". Puntos de vida restantes: " + tanque.getPuntosVida());
	            }
	            
	            // Verificamos si el tanque ha sido destruido
	            if (tanque.getPuntosVida() <= 0) {
	                logger.info("¡El " + tanque.getNombreVehiculo() + " ha sido destruido! La nave ha ganado la batalla.");
	                break;
	            }
	        }
	    }


}
