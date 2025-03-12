package com.calculator.dto.request;

import org.hibernate.annotations.Comment;
import jakarta.validation.constraints.NotNull;

@Comment(value = "Solicitud de cálculo")
public record CalculationRequestDTO(
                @NotNull(message = "num1 no puede ser nulo") double num1,
                @NotNull(message = "num2 no puede ser nulo") double num2) {
}
