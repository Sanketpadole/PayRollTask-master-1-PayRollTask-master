# Set the base image to use
FROM openjdk:17-jdk-alpine3.13

# Set the working directory
WORKDIR /app

# Copy the application jar file to the container
COPY target/my-spring-boot-app.jar /app/my-spring-boot-app.jar

# Expose the port used by the application
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "/app/my-spring-boot-app.jar"]







