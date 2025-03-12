package com.calculator.service.base;

import com.calculator.exception.ExternalServiceException;

public interface ExternalPercentageService {
    double fetchPercentage() throws ExternalServiceException;
}
