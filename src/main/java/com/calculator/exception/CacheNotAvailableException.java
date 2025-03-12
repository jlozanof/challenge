package com.calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class CacheNotAvailableException extends RuntimeException {
    public CacheNotAvailableException(String message) {
        super(message);
    }
}