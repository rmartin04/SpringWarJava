
package com.warspringbootjava.WarSpringJava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.warspringbootjava.WarSpringJava.entities.Guerrero;
import com.warspringbootjava.WarSpringJava.excepciones.FuerzaGuerreroException;
import com.warspringbootjava.WarSpringJava.excepciones.FuerzaYResistenciaException;
import com.warspringbootjava.WarSpringJava.excepciones.NumeroGuerrerosException;
import com.warspringbootjava.WarSpringJava.excepciones.ResistenciaGuerreroException;
import com.warspringbootjava.WarSpringJava.repositories.GuerreroRepository;

@Service
public class GuerreroService {
	
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GuerreroService.class);

	private final GuerreroRepository repository;

	public GuerreroService(GuerreroRepository repository) {
		this.repository = repository;
	}

	public Guerrero crearGuerrero(Guerrero guerrero) throws FuerzaYResistenciaException, FuerzaGuerreroException,
			ResistenciaGuerreroException, NumeroGuerrerosException {
		int fuerza = guerrero.getFuerzaBase();
		int resistencia = guerrero.getResistencia();
		int fuerzaYResistencia = fuerza + resistencia;
		long cantidadPorTipo = repository.countByTipo(guerrero.getTipo());

		if (fuerza < 0 || fuerza > 10) {
			throw new FuerzaGuerreroException("La fuerza debe estar entre 0 y 10");
		}
		if (resistencia < 0 || resistencia > 10) {
			throw new ResistenciaGuerreroException("La resistencia debe estar entre 0 y 10");
		}
		if (fuerzaYResistencia > 10) {
			throw new FuerzaYResistenciaException("La fuerza y resistencia deben estar entre 0 y 10");
		}

		if ("SOLDADO".equalsIgnoreCase(guerrero.getTipo()) && cantidadPorTipo >= 10) {
			throw new NumeroGuerrerosException("No se pueden crear más de 10 soldados");
		}
		if ("ALIENIGENA".equalsIgnoreCase(guerrero.getTipo()) && cantidadPorTipo >= 10) {
			throw new NumeroGuerrerosException("No se pueden crear más de 10 alienígenas");
		}

		return repository.save(guerrero);
	}

	public Optional<Guerrero> obtenerGuerreroPorId(Long id) {
		return repository.findById(id); // Devuelve un Optional en lugar de null
	}

	public List<Guerrero> listarGuerreros() {
		return repository.findAll();
	}

	public List<Guerrero> buscarGuerrerosPorTipo(String tipo) {
		return repository.findByTipo(tipo);
	}

	public Guerrero actualizarGuerrero(Guerrero guerrero) {
		if (guerrero.getId() == null || !repository.existsById(guerrero.getId())) {
			throw new IllegalArgumentException("El guerrero con ID " + guerrero.getId() + " no existe.");
		}
		return repository.save(guerrero);
	}

	public void eliminarGuerrero(Long id) {
		if (!repository.existsById(id)) {
			throw new IllegalArgumentException("El guerrero con ID " + id + " no existe.");
		}
		repository.deleteById(id);
	}

	/**
	 * Filtra guerreros opcionalmente por tipo y/o por fragmento de nombre. Si
	 * 'tipo' es nulo o vacío, se ignora. Si 'search' es nulo o vacío, se ignora.
	 */
	public List<Guerrero> filtrarPorTipoYNombre(String tipo, String search) {
		boolean tieneTipo = tipo != null && !tipo.isBlank();
		boolean tieneSearch = search != null && !search.isBlank();

		if (tieneTipo && tieneSearch) {
			return repository.findByTipoAndNombreContainingIgnoreCase(tipo, search);
		} else if (tieneTipo) {
			return repository.findByTipo(tipo);
		} else if (tieneSearch) {
			return repository.findByNombreContainingIgnoreCase(search);
		} else {
			return repository.findAll();
		}
	}
}
