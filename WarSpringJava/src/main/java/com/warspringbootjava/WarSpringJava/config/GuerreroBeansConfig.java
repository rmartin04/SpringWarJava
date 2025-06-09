package com.warspringbootjava.WarSpringJava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.warspringbootjava.WarSpringJava.entities.Guerrero;

@Configuration
public class GuerreroBeansConfig {
	
	@Bean("alienigenaPlantilla")
    public Guerrero alienigenaPlantilla()  {
        return new Guerrero("Alienígena base", "Alienígena", 5, 3);
    }

    @Bean("soldadoPlantilla")
    public Guerrero soldadoPlantilla()  {
        return new Guerrero("Soldado base", "Soldado", 4, 4);
    }

}
