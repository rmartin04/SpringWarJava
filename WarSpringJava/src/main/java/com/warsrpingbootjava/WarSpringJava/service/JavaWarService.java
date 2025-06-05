package com.warsrpingbootjava.WarSpringJava.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warsrpingbootjava.WarSpringJava.beans.NaveDestructoraBean;
import com.warsrpingbootjava.WarSpringJava.beans.TanqueBean;
import com.warsrpingbootjava.WarSpringJava.entities.Guerrero;
import com.warsrpingbootjava.WarSpringJava.entities.VehiculosGuerra;
import com.warsrpingbootjava.WarSpringJava.excepciones.FuerzaGuerreroException;
import com.warsrpingbootjava.WarSpringJava.excepciones.FuerzaYResistenciaException;
import com.warsrpingbootjava.WarSpringJava.excepciones.ResistenciaGuerreroException;
import com.warsrpingbootjava.WarSpringJava.interfaces.CheckedSupplier2.CheckedSupplier;

@Service
public class JavaWarService {
	private static final Logger logger = LoggerFactory.getLogger(JavaWarService.class);

	@Autowired
	TanqueBean tanque;
	@Autowired
	NaveDestructoraBean nave;

	private void ejecutarLucha() {
		try {
			simularLucha();
		} catch (Exception e) {
			logger.error("Ha habido un error durante la guerra: ", e.getMessage());
			e.printStackTrace();
		}
	}

	private NaveDestructoraBean crearNave()
			throws FuerzaYResistenciaException, FuerzaGuerreroException, ResistenciaGuerreroException, Exception {
		List<Guerrero> listadoAlienigenas = crearAlienigenas();
		nave = new NaveDestructoraBean("Nostromo", "U.S.C.S.S", 6, 4, listadoAlienigenas);
		logger.info(nave.toString());
		return nave;
	}

	private List<Guerrero> crearGuerreros(CheckedSupplier<Guerrero> constructor, int cantidad) throws Exception {
		List<Guerrero> guerreros = new ArrayList<>();
		for (int i = 0; i < cantidad; i++) {
			guerreros.add(constructor.get());
		}
		return guerreros;
	}

	private void aplicarDanio(VehiculosGuerra atacante, VehiculosGuerra defensor) {
		int ataque = atacante.atacar();
		int defensa = defensor.defender(ataque);
		int danioReal = Math.max(0, ataque - defensa);

		logger.info("Ataque de " + atacante.getNombreVehiculo() + ": " + ataque + " | Defensa de "
				+ defensor.getNombreVehiculo() + ": " + defensa + " | Daño real: " + danioReal);

		int nuevaDefensa = defensor.getDefensaBase() - danioReal;
		if (nuevaDefensa >= 0) {
			defensor.setDefensaBase(nuevaDefensa);
			logger.info("La defensa de " + defensor.getNombreVehiculo() + " se reduce a: " + nuevaDefensa);
		} else {
			defensor.setDefensaBase(0);
			int danioAPuntosVida = -nuevaDefensa;
			defensor.setPuntosVida(defensor.getPuntosVida() - danioAPuntosVida);
			logger.info("La defensa de " + defensor.getNombreVehiculo() + " se ha agotado. Daño a vida: "
					+ danioAPuntosVida + ". Puntos de vida restantes: " + defensor.getPuntosVida());
		}
	}

	public void simularLucha() {
		try {
			tanque = crearTanque();
			tanque.embarcarGuerreros();
			nave = crearNave();
			nave.embarcarGuerreros();
		} catch (Exception e) {
			logger.error("Error al crear los vehículos: ", e);
			return;
		}

		logger.info("\n\tLa batalla ha comenzado entre el " + tanque.getNombreVehiculo() + " y la "
				+ nave.getNombreVehiculo() + "!");

		while (tanque.getPuntosVida() > 0 && nave.getPuntosVida() > 0) {
			aplicarDanio(tanque, nave);
			if (nave.getPuntosVida() <= 0) {
				logger.info("¡La " + nave.getNombreVehiculo() + " ha sido destruida! El tanque ha ganado la batalla.");
				break;
			}

			aplicarDanio(nave, tanque);
			if (tanque.getPuntosVida() <= 0) {
				logger.info("¡El " + tanque.getNombreVehiculo() + " ha sido destruido! La nave ha ganado la batalla.");
				break;
			}
		}
	}

	private TanqueBean crearTanque()
			throws FuerzaYResistenciaException, FuerzaGuerreroException, ResistenciaGuerreroException, Exception {
		List<Guerrero> listadoHumanos = crearHumanos();
		TanqueBean tanque = new TanqueBean("el tigre real", "T-34", 6, 3, listadoHumanos);
		logger.info(tanque.toString());
		return tanque;
	}

	private List<Guerrero> crearHumanos()
			throws FuerzaYResistenciaException, FuerzaGuerreroException, ResistenciaGuerreroException, Exception {
		return crearGuerreros(() -> new Guerrero("Soldado", 6, 4), 10);
	}

	private List<Guerrero> crearAlienigenas()
			throws FuerzaYResistenciaException, FuerzaGuerreroException, ResistenciaGuerreroException, Exception {
		return crearGuerreros(() -> new Guerrero("Bicho", 6, 4), 10);
	}
}
