# New Tracker 
A task management backend built with Spring Boot and MySQL.

## What's Done

### Domain Models (`src/main/java/com/tracker/newtracker/models/`)
- `Task.java` - Core task entity
- `TaskList.java` - Groups tasks together
- `TaskPriority.java` - Task priority levels
- `TaskStatus.java` - Task status tracking

### DTOs (`src/main/java/com/tracker/newtracker/dtos/`)
- `TaskDto.java` - Data transfer object for tasks

### Configuration
- `build.gradle` - Project dependencies
- `application.properties` - Database config
- `Dockerfile` + `docker-compose.yml` - Container setup

## Tech
- Java 17
- Spring Boot 4.0.0
- Spring Data JPA
- MySQL
- Gradle

## Run It

```bash
# Start app
gradlew.bat bootRun

# Build
gradlew.bat clean build

# Test
gradlew.bat test