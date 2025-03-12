package com.calculator.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.calculator.entity.base.AuditLog;

//Interfaz para auditoría
public interface AuditLogService {
    void saveLog(AuditLog log);

    Page<AuditLog> getAuditLogs(Pageable pageable);
}
