package com.calculator.dto.response;

import lombok.Data;

//Single Responsibility Principle (SRP): SRP, OCP, DIP.
//Factory Method, creando respuestas estáticas success() y error().
@Data
public class ApiResponseDTO<T> {

    private boolean success; // Indica si la respuesta fue exitosa
    private T data; // Datos devueltos en una respuesta exitosa
    private String error; // Mensaje de error si ocurre un fallo

    public static <T> ApiResponseDTO<T> success(T data) {
        ApiResponseDTO<T> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static ApiResponseDTO<?> error(String errorMessage) {
        ApiResponseDTO<?> response = new ApiResponseDTO<>();
        response.setSuccess(false);
        response.setError(errorMessage);
        return response;
    }

}
