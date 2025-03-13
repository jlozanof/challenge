package com.calculator.event;

import com.calculator.entity.base.AuditLog;
import com.calculator.entity.enums.Endpoint;
import com.calculator.service.core.AuditLogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuditLogEventListenerTest {

    @Mock
    private AuditLogService auditLogService;

    @InjectMocks
    private AuditLogEventListener eventListener;

    @Test
    void handleAuditLogEvent_SavesLog() {
        // Crear evento de prueba
        AuditLogEvent event = new AuditLogEvent(
                Endpoint.CALCULATE,
                "num1=5&num2=5",
                "result=11.0",
                null,
                "127.0.0.1",
                HttpStatus.OK);

        eventListener.handleAuditLogEvent(event);

        verify(auditLogService).saveLog(any(AuditLog.class)); // Verifica que se guardó el log
    }
}