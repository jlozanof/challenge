package com.calculator.entity.enums;

import org.hibernate.annotations.Comment;

@Comment(value = "Se define enumeracion para los 2 casos solicitados")
public enum Endpoint {
    CALCULATE,
    HISTORY
}