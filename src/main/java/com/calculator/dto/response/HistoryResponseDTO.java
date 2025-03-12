package com.calculator.dto.response;

import org.springframework.data.domain.Page;

import com.calculator.entity.base.AuditLog;

import java.util.List;

public record HistoryResponseDTO(
        List<AuditLog> logs,
        int currentPage,
        int totalPages,
        long totalItems) {
    public static HistoryResponseDTO fromPage(Page<AuditLog> page) {
        return new HistoryResponseDTO(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements());
    }
}