
package com.warsrpingbootjava.WarSpringJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.warsrpingbootjava.WarSpringJava.entities.Guerrero;
import com.warsrpingbootjava.WarSpringJava.repositories.GuerreroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GuerreroService {

    @Autowired
    private GuerreroRepository guerreroRepository;

    public Guerrero crearGuerrero(Guerrero guerrero) {
        return guerreroRepository.save(guerrero);
    }

    public Optional<Guerrero> obtenerGuerreroPorId(Long id) {
        return guerreroRepository.findById(id); // Devuelve un Optional en lugar de null
    }

    public List<Guerrero> listarGuerreros() {
        return guerreroRepository.findAll();
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
