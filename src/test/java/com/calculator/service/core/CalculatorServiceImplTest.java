package com.calculator.service.core;

import com.calculator.dto.request.CalculationRequestDTO;
import com.calculator.exception.ExternalServiceException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Habilita Mockito
class CalculatorServiceImplTest {

    @Mock // Simula el servicio externo
    private ExternalPercentageService externalService;

    @Mock // Simula el caché
    private CacheManager cacheManager;

    @InjectMocks // Inyecta los mocks en esta clase
    private CalculatorServiceImpl calculatorService;

    @Test
    void calculate_Success() throws ExternalServiceException {
        // Configurar mock
        when(externalService.fetchPercentage()).thenReturn(10.0);

        // Ejecutar método
        CalculationRequestDTO request = new CalculationRequestDTO(5.0, 5.0);
        var result = calculatorService.calculateWithPercentage(request);

        // Verificar resultado
        assertEquals(11.0, result.result());
    }

    @Test
    void calculate_FallbackToCache() throws ExternalServiceException {
        // Simular fallo en servicio externo
        when(externalService.fetchPercentage()).thenThrow(ExternalServiceException.class);

        // Simular caché con valor 10.0
        Cache mockCache = mock(Cache.class);
        when(cacheManager.getCache("percentageCache")).thenReturn(mockCache);
        when(mockCache.get("percentageKey")).thenReturn(new SimpleValueWrapper(10.0));

        // Ejecutar método
        CalculationRequestDTO request = new CalculationRequestDTO(5.0, 5.0);
        var result = calculatorService.calculateWithPercentage(request);

        // Verificar resultado
        assertEquals(11.0, result.result());
    }
}