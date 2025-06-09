package com.warspringbootjava.WarSpringJava.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.warspringbootjava.WarSpringJava.interceptor.AuthInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
            .addPathPatterns("/**")
            .excludePathPatterns(
            		"/", 
                    "/login", 
                    "/registrarUsuario",   
                    "/logout",             
                    "/webjars/**",
                    "/assets/**" 
            );
    }

}
