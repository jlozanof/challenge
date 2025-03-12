package com.calculator.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;
// Configuración de Caché con Caffeine
// Habilita la caché y configura el tiempo de expiración y el tamaño máximo de la caché
// Se crea un bean de tipo CacheManager que utiliza Caffeine como proveedor de caché
// Se crea un bean de tipo Caffeine que configura el tiempo de expiración y el tamaño máximo de la caché
// Se utiliza la anotación @EnableCaching para habilitar la caché en la aplicación
// Se define un caché llamado "percentageCache" con una expiración de 30 minutos y un máximo de 100 entradas
// Se configura el tiempo de expiración en 30 minutos y el tamaño máximo en 100 entradas
// Se crea un bean de tipo CacheManager que utiliza Caffeine como proveedor de caché

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("percentageCache");
        cacheManager.setCaffeine(caffeineConfig());
        return cacheManager;
    }

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES) // Expiración de 30 min
                .maximumSize(100); // Máximo 100 entradas
    }
}