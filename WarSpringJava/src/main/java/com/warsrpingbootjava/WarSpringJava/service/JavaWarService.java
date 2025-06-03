package com.warsrpingbootjava.WarSpringJava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import presentacion.BatallaJavaWar;

public class JavaWarService {
	   private static final Logger logger = LoggerFactory.getLogger(JavaWarService.class);
	
	   private void ejecutarLucha() {
	        try {
	            simularLucha();
	        } catch (Exception e) {
	            logger.error("Ha habido un error durante la guerra: ", e.getMessage());
	            e.printStackTrace();
	        }
	    }

}
