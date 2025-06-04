
package com.warsrpingbootjava.WarSpringJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.warsrpingbootjava.WarSpringJava.entities.Guerrero;
import com.warsrpingbootjava.WarSpringJava.repositories.GuerreroRepository;

import java.util.List;

@Service
public class GuerreroService {

    @Autowired
    private GuerreroRepository guerreroRepository;

    public Guerrero crearGuerrero(Guerrero guerrero) {
        return guerreroRepository.save(guerrero);
    }

    public Guerrero obtenerGuerreroPorId(Long id) {
        return guerreroRepository.findById(id).orElse(null);
    }

    public List<Guerrero> listarGuerreros() {
        return guerreroRepository.findAll();
    }
    
	public Guerrero actualizarGuerrero(Guerrero guerrero) {
		if (guerrero.getId() == null || !guerreroRepository.existsById(guerrero.getId())) {
			return null; // O lanzar una excepción si se prefiere
		}
		return guerreroRepository.save(guerrero);
	}
    public void eliminarGuerrero(Long id) {
        guerreroRepository.deleteById(id);
    }
}
