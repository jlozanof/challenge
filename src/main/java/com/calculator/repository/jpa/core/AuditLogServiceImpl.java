package com.calculator.repository.jpa.core;

import com.calculator.entity.base.AuditLog;
import com.calculator.repository.jpa.base.AuditLogRepository;
import com.calculator.service.base.AuditLogService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // Requiere Lombok
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    @Async
    @Transactional
    @Override
    public void saveLog(AuditLog log) {
        auditLogRepository.save(log);
    }

    @Override
    public Page<AuditLog> getAuditLogs(Pageable pageable) {
        return auditLogRepository.findAll(pageable);
    }
}