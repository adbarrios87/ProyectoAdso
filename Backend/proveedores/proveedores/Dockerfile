# Etapa de construccion
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compilamos el proyecto omitiendo los tests para mayor velocidad
RUN mvn clean package -DskipTests

# Etapa de ejecucion
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# Copiamos el .war o .jar generado desde la etapa de construccion
COPY --from=build /app/target/proveedores-0.0.1-SNAPSHOT.jar app.jar
# Exponemos el puerto estandar
EXPOSE 8080
# Comando para iniciar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
