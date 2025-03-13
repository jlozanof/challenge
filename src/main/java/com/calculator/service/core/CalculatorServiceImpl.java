package com.calculator.service.core;

import com.calculator.dto.request.CalculationRequestDTO;
import com.calculator.dto.response.CalculationResponseDTO;
import com.calculator.exception.CacheNotAvailableException;
import com.calculator.exception.ExternalServiceException;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {

    private final ExternalPercentageService externalPercentageService;
    private final CacheManager cacheManager;

    @Override
    @Retryable(retryFor = ExternalServiceException.class, maxAttempts = 3)
    public CalculationResponseDTO calculateWithPercentage(CalculationRequestDTO request) {
        double percentage = getPercentageWithFallback();
        double sum = request.num1() + request.num2();
        double result = sum * (1 + percentage / 100);
        return new CalculationResponseDTO(result);
    }

    private double getPercentageWithFallback() {
        try {
            return externalPercentageService.fetchPercentage();
        } catch (ExternalServiceException ex) {
            return getCachedPercentage();
        }
    }

    private double getCachedPercentage() {
        Cache cache = cacheManager.getCache("percentageCache");
        if (cache == null) {
            throw new CacheNotAvailableException("Caché no configurada");
        }
        Cache.ValueWrapper wrapper = cache.get("percentageKey");

        if (wrapper == null || wrapper.get() == null) {
            throw new CacheNotAvailableException("No hay valor en caché");
        }

        Object value = wrapper.get();
        if (!(value instanceof Double)) {
            throw new CacheNotAvailableException("Valor en caché no es un Double");
        }

        return (Double) value; // Cast seguro
    }
}