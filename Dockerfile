# Set the base image to use
FROM openjdk:17-jdk-alpine3.13

# Set the working directory
WORKDIR /app

# Copy the application jar file to the container
COPY target/springboot-0.0.1-SNAPSHOT.jar /app/springboot-0.0.1-SNAPSHOT.jar


ARG DATABASE_HOST
ARG DATABASE_PORT
ARG DATABASE_NAME
ARG DATABASE_USERNAME
ARG DATABASE_PASSWORD








ENV PROFILE=$ACTIVE_PROFILE
ENV DATABASE_HOST=$DATABASE_HOST
ENV DATABASE_PORT=$DATABASE_PORT
ENV DATABASE_NAME=$DATABASE_NAME
ENV DATABASE_USERNAME=$DATABASE_USERNAME
ENV DATABASE_PASSWORD=$DATABASE_PASSWORD



































# Expose the port used by the application
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "/app/springboot-0.0.1-SNAPSHOT.jar"]







