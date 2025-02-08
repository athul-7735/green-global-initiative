# Use an official OpenJDK runtime as a base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose the application port
EXPOSE 9090

# Command to run the application ver01
ENTRYPOINT ["java", "-jar", "app.jar"]
