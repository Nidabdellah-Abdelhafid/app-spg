# Use official openjdk image
FROM openjdk:25-slim-bullseye

# Set working directory
WORKDIR /app

# Copy the JAR file from target folder
COPY target/adm_app.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
