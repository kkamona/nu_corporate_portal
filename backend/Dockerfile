FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY backend/. .
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
