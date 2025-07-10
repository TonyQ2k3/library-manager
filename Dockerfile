# Stage 1: Build the Spring Boot application
FROM maven:3.9-eclipse-temurin-17 AS build

# Set the working directory in the build stage
WORKDIR /app

# Copy the Maven wrapper files
COPY .mvn/ .mvn

# Copy the Maven wrapper and pom.xml
COPY mvnw pom.xml ./

# Give execute permission to the Maven wrapper script
RUN chmod +x ./mvnw

# Download dependencies and cache them
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application, skipping tests to reduce build time
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final Docker image
FROM openjdk:17-jdk-alpine

# Set the working directory in the final image
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

#  Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

# Set the default port for the application
EXPOSE 8080