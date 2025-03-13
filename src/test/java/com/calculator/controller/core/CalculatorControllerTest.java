package com.calculator.controller.core;

import com.calculator.dto.response.CalculationResponseDTO;
import com.calculator.service.core.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc; // Simula peticiones HTTP

    @MockitoBean
    private CalculatorService calculatorService;

    @Test
    void calculate_ValidRequest_ReturnsOk() throws Exception {
        // Configurar mock
        when(calculatorService.calculateWithPercentage(any()))
                .thenReturn(new CalculationResponseDTO(11.0));

        // Ejecutar petición POST
        mockMvc.perform(post("/api/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"num1\":5, \"num2\":5}"))
                .andExpect(status().isOk()) // Verificar código 200
                .andExpect(jsonPath("$.success").value(true)) // Verificar JSON
                .andExpect(jsonPath("$.data.resultado").value(11.0));
    }
}