package com.calculator.entity.base;

import lombok.Data;
import jakarta.persistence.*;

import org.hibernate.annotations.Comment;
import org.springframework.http.HttpStatus;

import com.calculator.entity.enums.Endpoint;

import java.time.LocalDateTime;

@Comment(value = "Tabla que permite el registro de auditoria de las operaciones realizadas en el sistema")
@Data
@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "consecutivo de la tabla")
    private Long id;

    @Comment(value = "Fecha y hora que ejecuta la operación")
    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Comment(value = "Endpoint invocado")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Endpoint endpoint;

    @Comment(value = "parametros recibidos en el endpoint")
    @Column(columnDefinition = "TEXT")
    private String parameters;

    @Comment(value = "Respuesta generada en caso de exito por el endpoint")
    @Column(columnDefinition = "TEXT")
    private String response;

    @Column(columnDefinition = "TEXT")
    private String error;

    @Column(nullable = false)
    private String clientIp;

    public void setStatus(HttpStatus status) {
        throw new UnsupportedOperationException("Unimplemented method 'setStatus'");
    }

}