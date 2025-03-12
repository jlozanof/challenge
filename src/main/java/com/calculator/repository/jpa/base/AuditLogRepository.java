package com.calculator.repository.jpa.base;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calculator.entity.base.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}