package com.calculator.controller.core;

import com.calculator.anotations.RateLimited;
import com.calculator.dto.request.CalculationRequestDTO;
import com.calculator.dto.response.ApiResponseDTO;
import com.calculator.dto.response.CalculationResponseDTO;
import com.calculator.event.AuditLogEvent;
import com.calculator.service.core.CalculatorService;
import com.calculator.entity.enums.Endpoint;

import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
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
        // Obtener IP del cliente de forma segura
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();

        if (requestAttributes == null) {
            throw new IllegalStateException("Contexto de solicitud no disponible");
        }
        HttpServletRequest httpRequest = requestAttributes.getRequest();
        String clientIp = httpRequest.getRemoteAddr();
        eventPublisher.publishEvent(new AuditLogEvent(
                Endpoint.CALCULATE,
                request,
                result,
                null, // error (null si es éxito)
                clientIp,
                HttpStatus.OK // Status HTTP
        ));

        return ResponseEntity.ok(ApiResponseDTO.success(result));
    }
}