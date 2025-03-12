package com.calculator.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import java.time.Duration;

//RateLimitConfig.java: Crea el Bucket de Bucket4j para control de tasas. Se configura una tasa de 3 solicitudes por minuto.
//Se crea un bean de tipo Bandwidth que define la tasa de 3 solicitudes por minuto.
// RateLimitConfig.java (debe compilarse sin errores)
//Bucket4j es la librería que implementa el algoritmo de Token Bucket para controlar la tasa de solicitudes (Rate Limiting).

@Configuration
public class RateLimitConfig {

    @Bean
    public Bucket bucket() {
        Bandwidth limit = Bandwidth.builder()
                .capacity(3) // 3 solicitudes por minuto
                .refillIntervally(3, Duration.ofMinutes(1)) // Recarga 3 tokens cada 1 minuto
                .build();

        return Bucket.builder().addLimit(limit).build();
    }
}