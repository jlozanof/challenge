package com.calculator.service.core;

import com.calculator.exception.ExternalServiceException;

public interface ExternalPercentageService {
    double fetchPercentage() throws ExternalServiceException;
}
