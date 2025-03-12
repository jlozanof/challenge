package com.calculator.dto.response;

import org.hibernate.annotations.Comment;

import com.fasterxml.jackson.annotation.JsonProperty;

@Comment(value = "Respuesta de la operación de cálculo")
public record CalculationResponseDTO(
                @JsonProperty("resultado") double result) {

}
