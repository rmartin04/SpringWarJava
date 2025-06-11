
package com.warspringbootjava.WarSpringJava.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.warspringbootjava.WarSpringJava.entities.Guerrero;
import com.warspringbootjava.WarSpringJava.entities.VehiculoGuerra;
import com.warspringbootjava.WarSpringJava.excepciones.AtaqueDefensaException;
import com.warspringbootjava.WarSpringJava.excepciones.AtaqueMuyPoderosoException;
import com.warspringbootjava.WarSpringJava.excepciones.DefensaMuyPoderosaException;
import com.warspringbootjava.WarSpringJava.excepciones.EmbarcarGuerrerosException;
import com.warspringbootjava.WarSpringJava.excepciones.VidaMaximaPermitidaException;
import com.warspringbootjava.WarSpringJava.repositories.GuerreroRepository;
import com.warspringbootjava.WarSpringJava.repositories.VehiculosGuerraRepository;

@Service
public class VehiculoService {
    private final VehiculosGuerraRepository repo;
    private final GuerreroRepository guerreroRepo;

    public VehiculoService(VehiculosGuerraRepository repo, GuerreroRepository guerreroRepo) {
        this.repo = repo;
        this.guerreroRepo = guerreroRepo;
    }

    public VehiculoGuerra guardarVehiculo(VehiculoGuerra v) throws VidaMaximaPermitidaException, AtaqueDefensaException, AtaqueMuyPoderosoException, DefensaMuyPoderosaException, EmbarcarGuerrerosException {
        validarVehiculo(v);
        return repo.save(v);
    }

    private void validarVehiculo(VehiculoGuerra v) throws VidaMaximaPermitidaException, AtaqueDefensaException, AtaqueMuyPoderosoException, DefensaMuyPoderosaException, EmbarcarGuerrerosException {
        if (v.getPuntosVida() > 1000) {
            throw new VidaMaximaPermitidaException("La vida máxima permitida es 1000.");
        }

        int suma = v.getAtaqueBase() + v.getDefensaBase();
        if (suma > 10) {
            throw new AtaqueDefensaException("La suma de ataque + defensa no puede superar 10.");
        }

        if (v.getAtaqueBase() > 10) {
            throw new AtaqueMuyPoderosoException("El ataque máximo permitido es 10.");
        }

        if (v.getDefensaBase() > 10) {
            throw new DefensaMuyPoderosaException("La defensa máxima permitida es 10.");
        }

        if (v.getGuerreros() != null && v.getGuerreros().size() > 10) {
            throw new EmbarcarGuerrerosException("No se pueden embarcar más de 10 guerreros.");
        }
    }

    public VehiculoGuerra obtenerVehiculoPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + id));
    }

    public List<VehiculoGuerra> listarVehiculos() {
        return repo.findAll();
    }

    public List<VehiculoGuerra> filtrar(String tipo, String search) {
        if (tipo != null && !tipo.isBlank() && search != null && !search.isBlank()) {
            return repo.findByTipoVehiculoAndNombreVehiculoContainingIgnoreCase(tipo, search);
        } else if (tipo != null && !tipo.isBlank()) {
            return repo.findByTipoVehiculo(tipo);
        } else if (search != null && !search.isBlank()) {
            return repo.findByNombreVehiculoContainingIgnoreCase(search);
        } else {
            return repo.findAll();
        }
    }

    public VehiculoGuerra embarcarGuerreros(Long vehiculoId, List<Long> idsGuerreros) throws EmbarcarGuerrerosException {
    	Guerrero guerrero = new Guerrero();
        VehiculoGuerra vehiculo = obtenerVehiculoPorId(vehiculoId);

        if (idsGuerreros == null || idsGuerreros.isEmpty()) {
            throw new EmbarcarGuerrerosException("Debe indicar al menos un guerrero.");
        }

        List<Guerrero> guerrerosAEmbarcar = guerreroRepo.findAllById(idsGuerreros);
        if (vehiculo.getGuerreros().size() + guerrerosAEmbarcar.size() > 10) {
            throw new EmbarcarGuerrerosException("Excede el límite máximo de " + guerrero.getTipo() + " por " + vehiculo.getTipoVehiculo() + " (10).");
        }

        for (Guerrero g : guerrerosAEmbarcar) {
            vehiculo.addGuerrero(g);
        }

        return repo.save(vehiculo);
    }

    public void eliminarVehiculo(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("El vehículo a eliminar no existe.");
        }
        repo.deleteById(id);
    }

}
