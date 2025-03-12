package com.calculator.config.aop;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.calculator.exception.RateLimitExceededException;
import static com.calculator.commons.GlobalMessageConstants.MSG_LIMITE_EXCEDIDO;

/*
(Aspect-Oriented Programming) AOP es un paradigma de programación que permite modularizar aspectos transversales a través de la separación de preocupaciones.
   RateLimitAspect.java: Aspecto que se encarga de aplicar la lógica de control de tasas.
  Se encarga de verificar si el bucket tiene capacidad para consumir una solicitud.    
 */
@Aspect
@Component
public class RateLimitAspect {

    @Autowired
    private Bucket bucket; // Inyecta el bean del Bucket

    @Around("@annotation(rateLimited)")
    public Object rateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        if (probe.isConsumed()) {
            return joinPoint.proceed();
        } else {

            throw new RateLimitExceededException(MSG_LIMITE_EXCEDIDO);
        }
    }
}

/*
 * @Around("@annotation(rateLimited)")
 * public Object rateLimit(ProceedingJoinPoint joinPoint, RateLimited
 * rateLimited) throws Throwable {
 * ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
 * HttpServletRequest request = ((ServletRequestAttributes)
 * RequestContextHolder.getRequestAttributes()).getRequest();
 * String clientIp = request.getRemoteAddr();
 * 
 * if (!probe.isConsumed()) {
 * throw new RateLimitExceededException("Límite de 3 RPM excedido para IP: " +
 * clientIp);
 * }
 * 
 * return joinPoint.proceed();
 * }
 * }
 */