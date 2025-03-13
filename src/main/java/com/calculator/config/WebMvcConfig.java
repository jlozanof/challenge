package com.calculator.config;

/** 
Personaliza comportamientos de Spring MVC (CORS, conversores, etc.). 
Permite solicitudes desde dominios específicos.
En este caso, se habilita CORS para permitir peticiones desde cualquier origen.
Se permite el uso de los métodos GET, POST
*/
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import jakarta.annotation.Nonnull;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @SuppressWarnings("null")
    @Override
    public void addCorsMappings(@Nonnull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST");
    }
}
