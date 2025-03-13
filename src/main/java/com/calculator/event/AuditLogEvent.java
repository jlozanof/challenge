package com.calculator.event;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.calculator.entity.enums.Endpoint;

public class AuditLogEvent {
    private final Endpoint endpoint;
    private final Object parameters;
    private final Object response;
    private final String error;
    private final String clientIp;
    private final HttpStatus status;
    private final LocalDateTime timestamp;

    public AuditLogEvent(
            Endpoint endpoint,
            Object parameters,
            Object response,
            String error,
            String clientIp,
            HttpStatus status) {
        this.endpoint = endpoint;
        this.parameters = parameters;
        this.response = response;
        this.error = error;
        this.clientIp = clientIp;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public Endpoint getEndpoint() {
        return endpoint;
    }

    public Object getParameters() {
        return parameters;
    }

    public Object getResponse() {
        return response;
    }

    public String getError() {
        return error;
    }

    public String getClientIp() {
        return clientIp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}