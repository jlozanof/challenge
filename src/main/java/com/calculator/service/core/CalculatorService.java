package com.calculator.service.core;

import com.calculator.dto.request.CalculationRequestDTO;
import com.calculator.dto.response.CalculationResponseDTO;

//Interfaz del servicio de cálculo
public interface CalculatorService {
    CalculationResponseDTO calculateWithPercentage(CalculationRequestDTO request);

}
