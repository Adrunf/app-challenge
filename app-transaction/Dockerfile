FROM ubuntu:latest
LABEL authors="arobayo"
# Distroless Java 17
FROM gcr.io/distroless/java17-debian11

# Exponer puerto (cambiar si usas otro)
EXPOSE 8082

WORKDIR /app


# Copia el archivo JAR generado al contenedor
COPY app-microservices-services/build/libs/*.jar /app/app.jar

# Definir la variable de entorno por defecto
ENV SPRING_PROFILES_ACTIVE=docker

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
