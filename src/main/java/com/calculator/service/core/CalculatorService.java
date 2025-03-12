package com.calculator.service.base;

import com.calculator.dto.request.CalculationRequestDTO;
import com.calculator.dto.response.CalculationResponseDTO;

//Interfaz del servicio de cálculo
public interface CalculatorService {
    CalculationResponseDTO calculateWithPercentage(CalculationRequestDTO request);

}
