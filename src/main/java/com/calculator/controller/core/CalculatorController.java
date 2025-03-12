package com.calculator.controller.core;

import com.calculator.anotations.RateLimited;
import com.calculator.dto.request.CalculationRequestDTO;
import com.calculator.dto.response.ApiResponseDTO;
import com.calculator.dto.response.CalculationResponseDTO;
import com.calculator.event.AuditLogEvent;
import com.calculator.service.core.CalculatorService;
import com.calculator.entity.enums.Endpoint;

import jakarta.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.calculator.controller.constants.ApiEndPointsConstant.API_CALCULATOR;

@RestController
@RequestMapping(API_CALCULATOR)
public class CalculatorController {

    private final CalculatorService calculatorService;
    private final ApplicationEventPublisher eventPublisher;

    public CalculatorController(
            CalculatorService calculatorService,
            ApplicationEventPublisher eventPublisher) {
        this.calculatorService = calculatorService;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping
    @RateLimited
    public ResponseEntity<ApiResponseDTO<CalculationResponseDTO>> calculate(
            @Valid @RequestBody CalculationRequestDTO request) {
        CalculationResponseDTO result = calculatorService.calculateWithPercentage(request);

        eventPublisher.publishEvent(new AuditLogEvent(
                Endpoint.CALCULATE,
                request,
                result,
                null));

        return ResponseEntity.ok(ApiResponseDTO.success(result));
    }
}