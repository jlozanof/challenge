package com.calculator.event;

import com.calculator.entity.enums.Endpoint;

public class AuditLogEvent {
    private Endpoint endpoint;
    private Object request;
    private Object response;
    private String error;

    public AuditLogEvent(Endpoint endpoint, Object request, Object response, String error) {
        this.endpoint = endpoint;
        this.request = request;
        this.response = response;
        this.error = error;
    }

    // Getters
    public Endpoint getEndpoint() {
        return endpoint;
    }

    public Object getRequest() {
        return request;
    }

    public Object getResponse() {
        return response;
    }

    public String getError() {
        return error;
    }
}