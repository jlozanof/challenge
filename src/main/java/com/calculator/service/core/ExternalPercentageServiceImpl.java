package com.calculator.service.core;

import com.calculator.exception.ExternalServiceException;
import com.calculator.service.base.ExternalPercentageService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Random;

//Interfaz para obtener el porcentaje 
@Service
public class ExternalPercentageServiceImpl implements ExternalPercentageService {

    // Mock: Retorna 10% o falla aleatoriamente (30% de probabilidad de fallo)
    @Override
    @Cacheable(value = "percentageCache", key = "'currentPercentage'")
    public double fetchPercentage() throws ExternalServiceException {
        // Simular fallo del servicio externo (30% de probabilidad)
        if (new Random().nextDouble() < 0.3) {
            throw new ExternalServiceException("Error al obtener el porcentaje del servicio externo");
        }

        // Valor fijo del 10% (simula respuesta exitosa)
        return 10.0;
    }
}