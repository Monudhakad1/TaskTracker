# We use Eclipse Temurin which is the industry standard now
FROM eclipse-temurin:17-jdk

# Copy the jar
COPY target/*.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "/app.jar"]