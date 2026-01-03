# Base image: Java 21
FROM eclipse-temurin:21-jdk

# Copy Spring Boot jar into container
COPY build/libs/NewTracker-0.0.1-SNAPSHOT.jar app.jar

# Document the port (optional but good practice)
EXPOSE 8080
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

