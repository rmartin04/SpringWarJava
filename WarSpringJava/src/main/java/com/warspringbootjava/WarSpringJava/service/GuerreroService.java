
package com.warspringbootjava.WarSpringJava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warspringbootjava.WarSpringJava.entities.Guerrero;
import com.warspringbootjava.WarSpringJava.excepciones.FuerzaGuerreroException;
import com.warspringbootjava.WarSpringJava.excepciones.FuerzaYResistenciaException;
import com.warspringbootjava.WarSpringJava.excepciones.NumeroGuerrerosException;
import com.warspringbootjava.WarSpringJava.excepciones.ResistenciaGuerreroException;
import com.warspringbootjava.WarSpringJava.repositories.GuerreroRepository;

@Service
public class GuerreroService {

    @Autowired
    private GuerreroRepository guerreroRepository;

    public Guerrero crearGuerrero(Guerrero guerrero) throws FuerzaYResistenciaException, FuerzaGuerreroException, ResistenciaGuerreroException, NumeroGuerrerosException {
    	int fuerza = guerrero.getFuerzaBase();
    	int resistencia = guerrero.getResistencia();
    	int fuerzaYResistencia = fuerza + resistencia;
    	
    	if (fuerza < 0 || fuerza > 10) {
            throw new FuerzaGuerreroException("La fuerza debe estar entre 0 y 10");
        }
        if (resistencia < 0 || resistencia > 10) {
            throw new ResistenciaGuerreroException("La resistencia debe estar entre 0 y 10");
        }
        if (fuerzaYResistencia > 10) {
        	throw new FuerzaYResistenciaException("La fuerza y resistencia deben estar entre 0 y 10");
        }
        
        if (guerreroRepository.count() > 10) {
			throw new NumeroGuerrerosException("No se pueden crear más de 10 guerreros");
		}
        return guerreroRepository.save(guerrero);
    }

    public Optional<Guerrero> obtenerGuerreroPorId(Long id) {
        return guerreroRepository.findById(id); // Devuelve un Optional en lugar de null
    }

    public List<Guerrero> listarGuerreros() {
        return guerreroRepository.findAll();
    }
    
    public List<Guerrero> buscarGuerrerosPorTipo(String tipo) {
		return guerreroRepository.findByTipo(tipo);
	}

    public Guerrero actualizarGuerrero(Guerrero guerrero) {
        if (guerrero.getId() == null || !guerreroRepository.existsById(guerrero.getId())) {
            throw new IllegalArgumentException("El guerrero con ID " + guerrero.getId() + " no existe.");
        }
        return guerreroRepository.save(guerrero);
    }

    public void eliminarGuerrero(Long id) {
        if (!guerreroRepository.existsById(id)) {
            throw new IllegalArgumentException("El guerrero con ID " + id + " no existe.");
        }
        guerreroRepository.deleteById(id);
    }
}
