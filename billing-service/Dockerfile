# Build Stage
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Pre-install dependencies (including protobuf tools)
RUN ./mvnw dependency:go-offline

COPY src ./src

# Explicitly install os-maven-plugin extension to handle architecture detection in Docker
RUN ./mvnw clean package -DskipTests

# Run Stage
FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
COPY --from=build /app/target/billing-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 4001 9001
ENTRYPOINT ["java", "-jar", "app.jar"]