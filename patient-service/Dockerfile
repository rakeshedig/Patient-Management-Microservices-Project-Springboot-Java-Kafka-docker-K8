# Build Stage
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
# Download dependencies first to leverage cache
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Run Stage
FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
COPY --from=build /app/target/patient-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 4000
ENTRYPOINT ["java", "-jar", "app.jar"]
