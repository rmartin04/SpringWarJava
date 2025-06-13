package com.warspringbootjava.WarSpringJava.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.warspringbootjava.WarSpringJava.entities.Guerrero;
import com.warspringbootjava.WarSpringJava.entities.VehiculoGuerra;
import com.warspringbootjava.WarSpringJava.repositories.GuerreroRepository;
import com.warspringbootjava.WarSpringJava.repositories.VehiculosGuerraRepository;

import jakarta.transaction.Transactional;

@Configuration
public class DataInitializer {
	
	@Bean
    @Transactional
    public CommandLineRunner initData(GuerreroRepository guerreroRepo, VehiculosGuerraRepository vehiculoRepo) {
        return args -> {
            if (guerreroRepo.count() == 0) {
            	
            	// Soldados (solo en TANQUES)
            	Guerrero soldado1 = new Guerrero("John Price", "Soldado", 3, 7);
            	Guerrero soldado2 = new Guerrero("Ramirez", "Soldado", 6, 4);
            	Guerrero soldado3 = new Guerrero("Vasquez", "Soldado", 4, 6);
            	Guerrero soldado4 = new Guerrero("Ghost", "Soldado", 9, 1);
            	Guerrero soldado5 = new Guerrero("Hudson", "Soldado", 5, 4);
            	Guerrero soldado6 = new Guerrero("Reyes", "Soldado", 6, 3);
            	Guerrero soldado7 = new Guerrero("Díaz", "Soldado", 4, 5);
            	Guerrero soldado8 = new Guerrero("Marck Walkberg", "Soldado", 7, 3);
            	Guerrero soldado9 = new Guerrero("Connor", "Soldado", 3, 6);

            	// Alienígenas (solo en NAVES)
            	Guerrero alien1 = new Guerrero("Xenomorph", "Alienigena", 5, 5);
            	Guerrero alien2 = new Guerrero("Predator", "Alienigena", 9, 1);
            	Guerrero alien3 = new Guerrero("Zorg", "Alienigena", 7, 3);
            	Guerrero alien4 = new Guerrero("Thanos", "Alienigena", 8, 2);
            	Guerrero alien5 = new Guerrero("Vega", "Alienigena", 6, 4);
            	Guerrero alien6 = new Guerrero("Nova", "Alienigena", 5, 5);
            	Guerrero alien7 = new Guerrero("Eclipse", "Alienigena", 4, 4);
            	Guerrero alien8 = new Guerrero("Void", "Alienigena", 3, 6);
            	Guerrero alien9 = new Guerrero("Zarek", "Alienigena", 6, 3);

                // Vehiculos de tipo nave
                VehiculoGuerra f22Raptor = new VehiculoGuerra();
                f22Raptor.setNombreVehiculo("F-22 Raptor");
                f22Raptor.setTipoVehiculo("Nave");
                f22Raptor.setPuntosVida(850);
                f22Raptor.setAtaqueBase(9);
                f22Raptor.setDefensaBase(7);

                VehiculoGuerra blackHawk = new VehiculoGuerra();
                blackHawk.setNombreVehiculo("Black Hawk");
                blackHawk.setTipoVehiculo("Nave");
                blackHawk.setPuntosVida(700);
                blackHawk.setAtaqueBase(6);
                blackHawk.setDefensaBase(6);

                VehiculoGuerra apache = new VehiculoGuerra();
                apache.setNombreVehiculo("AH-64 Apache");
                apache.setTipoVehiculo("Nave");
                apache.setPuntosVida(780);
                apache.setAtaqueBase(8);
                apache.setDefensaBase(5);
                
                // Vehiculos de tipo tanque
                VehiculoGuerra abrams = new VehiculoGuerra();
                abrams.setNombreVehiculo("M1 Abrams");
                abrams.setTipoVehiculo("Tanque");
                abrams.setPuntosVida(950);
                abrams.setAtaqueBase(8);
                abrams.setDefensaBase(9);

                VehiculoGuerra leopard2 = new VehiculoGuerra();
                leopard2.setNombreVehiculo("Leopard 2");
                leopard2.setTipoVehiculo("Tanque");
                leopard2.setPuntosVida(920);
                leopard2.setAtaqueBase(7);
                leopard2.setDefensaBase(9);

                VehiculoGuerra t90 = new VehiculoGuerra();
                t90.setNombreVehiculo("T-90");
                t90.setTipoVehiculo("Tanque");
                t90.setPuntosVida(880);
                t90.setAtaqueBase(7);
                t90.setDefensaBase(8);

                VehiculoGuerra merkava = new VehiculoGuerra();
                merkava.setNombreVehiculo("Merkava IV");
                merkava.setTipoVehiculo("Tanque");
                merkava.setPuntosVida(910);
                merkava.setAtaqueBase(6);
                merkava.setDefensaBase(10);
                
             // Soldados a TANQUES
                abrams.addGuerrero(soldado1);
                abrams.addGuerrero(soldado2);
                leopard2.addGuerrero(soldado3);
                leopard2.addGuerrero(soldado4);
                t90.addGuerrero(soldado5);
                t90.addGuerrero(soldado6);
                merkava.addGuerrero(soldado7);
                merkava.addGuerrero(soldado8);
                merkava.addGuerrero(soldado9);

                // Alienígenas a NAVES
                f22Raptor.addGuerrero(alien1);
                f22Raptor.addGuerrero(alien2);
                f22Raptor.addGuerrero(alien3);
                blackHawk.addGuerrero(alien4);
                blackHawk.addGuerrero(alien5);
                blackHawk.addGuerrero(alien6);
                apache.addGuerrero(alien7);
                apache.addGuerrero(alien8);
                apache.addGuerrero(alien9);
                
	             vehiculoRepo.saveAll(List.of(f22Raptor, blackHawk, apache, abrams, leopard2, t90, merkava));

            }
        };
    }

}
