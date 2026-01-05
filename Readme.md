# NewTracker – Task Management REST API

A Spring Boot backend application for managing tasks and task lists efficiently with clean architecture and MySQL database integration.

---

## Problem Statement

Managing tasks across different lists becomes difficult as data grows.  
Organizing, tracking progress, and updating task statuses efficiently is a common challenge.  
This project solves the problem by providing structured REST APIs with proper layered architecture and data validation.

---

## Features

- Create, update, and delete tasks and task lists
- Task status tracking (OPEN, IN_PROGRESS, CLOSED)
- Priority-based task management (LOW, MEDIUM, HIGH, URGENT)
- Automatic progress calculation for task lists
- Global exception handling with proper error responses
- Docker support for easy deployment

---

## Tech Stack

- Java 21
- Spring Boot 3.3.0
- Spring Data JPA
- MySQL 8.0
- Redis (configured)
- Docker & Docker Compose
- Gradle

---

## Architecture Overview

The application follows a layered architecture:

- **Controller layer** handles HTTP requests and responses
- **Service layer** contains business logic and validation
- **Repository layer** manages database operations with Spring Data JPA
- **Mapper layer** converts between entities and DTOs
- **DTOs** separate API contracts from internal database models

---

## Data Structures Used

- **UUID** for unique entity identification
- **Enum** for task priority and status management
- **One-to-Many relationship** with cascade operations for task lists and tasks
- **Bidirectional mapping** between task lists and tasks for efficient queries

---

## API Endpoints

### Task Lists
- `POST /task-lists` – Create task list
- `GET /task-lists` – Get all task lists
- `GET /task-lists/{id}` – Get task list by ID
- `PUT /task-lists/{id}` – Update task list
- `DELETE /task-lists/{id}` – Delete task list

### Tasks
- `POST /task-lists/{listId}/tasks` – Create task
- `GET /task-lists/{listId}/tasks` – Get all tasks in a list
- `GET /task-lists/{listId}/tasks/{taskId}` – Get task by ID
- `PUT /task-lists/{listId}/tasks/{taskId}` – Update task
- `DELETE /task-lists/{listId}/tasks/{taskId}` – Delete task

---

## How to Run

### With Docker (Recommended)

```bash
docker-compose up -d
```

Application will be available at `http://localhost:8080`

### Without Docker

1. Create MySQL database:
   ```sql
   CREATE DATABASE tasktracker;
   ```

2. Configure `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/tasktracker
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

3. Run the application:
   ```bash
   ./gradlew bootRun
   ```

---

## Database Schema

### task_list
- `id` (UUID) – Primary Key
- `title` (VARCHAR) – Task list name
- `description` (TEXT) – Task list description
- `created` (DATETIME) – Creation timestamp
- `updated` (DATETIME) – Last update timestamp

### tasks
- `id` (UUID) – Primary Key
- `title` (VARCHAR) – Task name
- `description` (TEXT) – Task details
- `status` (ENUM) – OPEN, IN_PROGRESS, CLOSED
- `priority` (ENUM) – LOW, MEDIUM, HIGH, URGENT
- `due_date` (DATETIME) – Task deadline
- `tasklist_id` (UUID) – Foreign Key to task_list
- `created` (DATETIME) – Creation timestamp
- `updated` (DATETIME) – Last update timestamp

---

## Future Enhancements

- Spring Security with JWT-based authentication and authorization
- Redis caching implementation for improved performance
- Role-based access control (RBAC)
- Task search and filtering capabilities
- Task assignment and collaboration features
- Email notifications for due dates
- Cloud deployment on AWS/Azure




