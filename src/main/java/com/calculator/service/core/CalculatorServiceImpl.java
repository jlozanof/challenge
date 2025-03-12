package com.calculator.service.core;

import com.calculator.dto.request.CalculationRequestDTO;
import com.calculator.dto.response.CalculationResponseDTO;
import com.calculator.exception.CacheNotAvailableException;
import com.calculator.exception.ExternalServiceException;
import com.calculator.service.base.CalculatorService;
import com.calculator.service.base.ExternalPercentageService;

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
        return (double) wrapper.get();
    }
}