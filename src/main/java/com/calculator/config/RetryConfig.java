package com.calculator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

//Habilita reintentos con @EnableRetry. La configuración de reintentos se manejará con anotaciones en el servicio.
@Configuration
@EnableRetry // Habilita Spring Retry
public class RetryConfig {
    // Configuración de reintentos se manejará con anotaciones en el servicio
}