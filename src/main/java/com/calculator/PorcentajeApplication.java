package com.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableRetry // Para @Retryable
@EnableAsync // Para @Async
@EnableCaching // Para @Cacheable
@EnableAspectJAutoProxy // Habilita AOP en la aplicación
public class PorcentajeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PorcentajeApplication.class, args);
	}

}
