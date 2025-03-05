FROM openjdk:21-jdk-slim

# Install Maven
RUN apt-get update \
    && apt-get install -y maven \
    && rm -rf /var/lib/apt/lists/*

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files into the container
COPY pom.xml .
COPY src ./src

# Clean and build the Maven project
RUN mvn clean install -DskipTests

# Copy the built JAR file into the container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose the application port
EXPOSE 9090

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
