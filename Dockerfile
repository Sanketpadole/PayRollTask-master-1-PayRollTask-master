FROM openjdk:17-jdk-alpine3.13

# Set the working directory
WORKDIR /app

# Copy the application jar file to the container
COPY target/springboot-0.0.1-SNAPSHOT.jar /app/springboot-0.0.1-SNAPSHOT.jar

# Set environment variables for database configuration


# Expose the port used by the application
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "/app/springboot-0.0.1-SNAPSHOT.jar"]








