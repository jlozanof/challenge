package com.calculator.event;

import com.calculator.entity.base.AuditLog;
import com.calculator.service.core.AuditLogService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AuditLogEventListener {

    private final AuditLogService auditLogService;

    public AuditLogEventListener(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @Async
    @EventListener
    public void handleAuditLogEvent(AuditLogEvent event) {
        // Lógica para convertir el evento en una entidad AuditLog y guardarla
        AuditLog log = new AuditLog();
        log.setTimestamp(event.getTimestamp());
        log.setEndpoint(event.getEndpoint());
        log.setParameters(event.getParameters().toString());
        log.setResponse(event.getResponse().toString());
        log.setError(event.getError());
        log.setClientIp(event.getClientIp());
        log.setStatus(event.getStatus());

        auditLogService.saveLog(log);
    }
}