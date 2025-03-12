package com.calculator.commons;

import com.calculator.exception.CacheNotAvailableException;
import com.calculator.exception.RateLimitExceededException;
import com.calculator.dto.response.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RateLimitExceededException.class)
    public ResponseEntity<ApiResponseDTO<?>> handleRateLimitExceeded(RateLimitExceededException ex,
            WebRequest request) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(ApiResponseDTO.error(ex.getMessage()));
    }

    @ExceptionHandler(CacheNotAvailableException.class)
    public ResponseEntity<ApiResponseDTO<?>> handleCacheNotAvailable(CacheNotAvailableException ex,
            WebRequest request) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponseDTO.error(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<?>> handleGenericException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponseDTO.error("Error interno del servidor"));
    }
}