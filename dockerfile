# Multi-stage build: Separa dependencias de build de runtime
# Stage 1: Build con Maven y JDK 21
FROM maven:3.9.5-eclipse-temurin-21-alpine AS builder

WORKDIR /app
COPY pom.xml .
# Descarga dependencias primero (cacheado en capa separada)
RUN mvn dependency:go-offline
COPY src/ ./src/
# Empaqueta la aplicación
RUN mvn clean package -DskipTests

# Stage 2: Runtime con JRE 21
FROM eclipse-temurin:21-alpine
WORKDIR /app


# Crear usuario no-root para seguridad. No valido para Alpine
#RUN groupadd -r spring && useradd -r -g spring spring
#USER spring

# Copiar artefacto desde stage de build
COPY --from=builder --chown=spring:spring /app/target/*.jar app.jar

# Configuraciones de salud y métricas
# Healthcheck: Verificación automática de salud
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]