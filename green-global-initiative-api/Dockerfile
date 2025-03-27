# Stage 1: Build stage
FROM maven:3.9.6-eclipse-temurin-21 as build

# Set the working directory inside the container
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Use an ARG for the build profile with 'development' as default
ARG PROFILE=development

# Build the project (use --no-transfer-progress to speed up)
RUN mvn clean package -Dspring.profiles.active=${PROFILE} -DskipTests --no-transfer-progress

# Stage 2: Runtime stage with a minimal JDK
FROM eclipse-temurin:21-jre-alpine

# Set working directory inside the container
WORKDIR /app

# Copy only the built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]