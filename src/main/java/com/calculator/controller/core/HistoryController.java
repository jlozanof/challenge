package com.calculator.controller.core;

import com.calculator.dto.response.ApiResponseDTO;
import com.calculator.dto.response.HistoryResponseDTO;
import com.calculator.entity.base.AuditLog;
import com.calculator.service.base.AuditLogService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.calculator.controller.constants.ApiEndPointsConstant.API_HISTORY;

@RestController
@RequestMapping(API_HISTORY)
public class HistoryController {
    private final AuditLogService auditLogService;

    public HistoryController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<HistoryResponseDTO>> getHistory(Pageable pageable) {
        Page<AuditLog> auditLogs = auditLogService.getAuditLogs(pageable);
        HistoryResponseDTO response = HistoryResponseDTO.fromPage(auditLogs); // Método estático
        return ResponseEntity.ok(ApiResponseDTO.success(response));
    }
}