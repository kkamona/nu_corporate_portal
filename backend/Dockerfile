FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY backend/. .
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17
WORKDIR /app

RUN mkdir -p /app/uploads/profilePictures

COPY --from=builder /app/target/*.jar app.jar

ENV UPLOADS_DIR=/app/uploads/profilePictures

ENTRYPOINT ["java", "-jar", "app.jar"]
