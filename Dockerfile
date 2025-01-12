# Use official openjdk image
FROM openjdk:11-jre-slim

# Set working directory
WORKDIR /app

# Copy the JAR file from target folder
COPY target/projet_adm-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose port 8080
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
