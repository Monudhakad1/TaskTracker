# NewTracker - Task Management REST API

A production-ready task management backend service built with Spring Boot, JPA, and MySQL. This application provides a complete RESTful API for managing tasks and task lists with proper layered architecture, data validation, and exception handling.

---

## 📋 Table of Contents
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Architecture](#-project-architecture)
- [API Endpoints](#-api-endpoints)
- [Getting Started](#-getting-started)
- [Configuration](#-configuration)
- [Next Steps](#-next-steps)

---

## ✨ Features

### Currently Implemented
- ✅ **Complete CRUD Operations** for Tasks and Task Lists
- ✅ **RESTful API Design** with proper HTTP methods and status codes
- ✅ **Entity-DTO Pattern** for clean API contracts
- ✅ **Mapper Layer** for converting between entities and DTOs
- ✅ **Service Layer** with business logic and validation
- ✅ **Repository Layer** with Spring Data JPA
- ✅ **Global Exception Handling** for consistent error responses
- ✅ **Task Progress Tracking** with automatic calculation
- ✅ **Enum Support** for Task Priority (LOW, MEDIUM, HIGH, URGENT) and Status (OPEN, IN_PROGRESS, CLOSED)
- ✅ **MySQL Database Integration** with JPA/Hibernate
- ✅ **Docker Support** with docker-compose for easy deployment
- ✅ **Redis Configuration** (prepared for caching)
- ✅ **Spring Boot Actuator** for monitoring endpoints

---

## 🛠️ Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 21 | Programming Language |
| **Spring Boot** | 3.3.0 | Application Framework |
| **Spring Data JPA** | 3.3.0 | Database Abstraction Layer |
| **Hibernate** | 6.x | ORM Implementation |
| **MySQL** | 8.0 | Relational Database |
| **Redis** | Latest | Caching (configured, ready to implement) |
| **Lombok** | 1.18.30 | Boilerplate Code Reduction |
| **Gradle** | 8.x | Build Tool |
| **Docker** | Latest | Containerization |

---

## 🏗️ Project Architecture

### Layered Architecture Pattern

```
┌─────────────────────────────────────────┐
│         Controller Layer                │  ← REST API Endpoints
│  (TaskController, TaskListController)   │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│          Mapper Layer                   │  ← Entity ↔ DTO Conversion
│  (TaskMapper, TaskListMapper + Impl)    │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│          Service Layer                  │  ← Business Logic & Validation
│  (TaskService, TaskListService + Impl)  │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│        Repository Layer                 │  ← Database Operations
│  (TaskRepository, TaskListRepository)   │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│         Database (MySQL)                │  ← Data Persistence
└─────────────────────────────────────────┘
```

### Package Structure

```
com.tracker.newtracker/
├── controllers/              # REST Controllers
│   ├── TaskController.java
│   ├── TaskListController.java
│   └── GlobalExceptionHandler.java
│
├── services/                 # Business Logic Interfaces
│   ├── TaskService.java
│   ├── TaskListService.java
│   └── impl/                 # Service Implementations
│       ├── taskServiceImpl.java
│       └── TaskListServiceImpl.java
│
├── repositories/             # Data Access Layer
│   ├── TaskRepository.java
│   └── TaskListRepository.java
│
├── mappers/                  # Entity-DTO Mappers
│   ├── TaskMapper.java
│   ├── TaskListMapper.java
│   └── IMPL/                 # Mapper Implementations
│       ├── TaskMapperImpl.java
│       └── TaskListMapperImpl.java
│
├── models/                   # Domain Entities
│   ├── Task.java
│   ├── TaskList.java
│   ├── TaskPriority.java (Enum)
│   ├── TaskStatus.java (Enum)
│   └── dtos/                 # Data Transfer Objects
│       ├── TaskDto.java
│       ├── TaskListDto.java
│       └── ErrorResponse.java
│
└── NewTrackerApplication.java  # Main Application Class
```

---

## 🔌 API Endpoints

### Base URL
```
http://localhost:8080
```

### TaskList Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| `GET` | `/task-lists` | Get all task lists | - | `List<TaskListDto>` |
| `POST` | `/task-lists` | Create new task list | `TaskListDto` | `TaskListDto` |
| `GET` | `/task-lists/{task_list_id}` | Get task list by ID | - | `TaskListDto` |
| `PUT` | `/task-lists/{task_list_id}` | Update task list | `TaskListDto` | `TaskListDto` |
| `DELETE` | `/task-lists/{task_list_id}` | Delete task list | - | `204 No Content` |

### Task Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| `GET` | `/task-lists/{task_list_id}/tasks` | Get all tasks in a list | - | `List<TaskDto>` |
| `POST` | `/task-lists/{task_list_id}/tasks` | Create new task | `TaskDto` | `TaskDto` |
| `GET` | `/task-lists/{task_list_id}/tasks/{task_id}` | Get task by ID | - | `TaskDto` |
| `PUT` | `/task-lists/{task_list_id}/tasks/{task_id}` | Update task | `TaskDto` | `TaskDto` |
| `DELETE` | `/task-lists/{task_list_id}/tasks/{task_id}` | Delete task | - | `204 No Content` |

### Request/Response Examples

#### Create TaskList
```json
POST /task-lists
{
  "title": "Work Tasks",
  "description": "All work-related tasks"
}
```

#### Create Task
```json
POST /task-lists/{task_list_id}/tasks
{
  "title": "Complete API Documentation",
  "description": "Write comprehensive API docs",
  "status": "OPEN",
  "priority": "HIGH",
  "dueDate": "2025-12-31T23:59:59"
}
```

#### Response Format
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "title": "Work Tasks",
  "description": "All work-related tasks",
  "count": 5,
  "progress": 0.6,
  "tasks": [   ]
}
```

### Error Response Format
```json
{
  "status": 400,
  "message": "Task title cannot be null or blank",
  "details": "uri=/task-lists/abc123/tasks"
}
```

---

## 🚀 Getting Started

### Prerequisites
- **JDK 21** (configured in build.gradle)
- **MySQL 8.0+** or Docker
- **Gradle** (wrapper included)
- **Redis** (optional, for caching implementation)

### Option 1: Run with Docker (Recommended)

```bash
# Start MySQL and application together
docker-compose up -d

# Application will be available at http://localhost:8080
# MySQL will be available at localhost:3307
```

### Option 2: Run Locally

1. **Setup MySQL Database**
```sql
CREATE DATABASE tasktracker;
```

2. **Configure Database Connection**
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tasktracker
spring.datasource.username=root
spring.datasource.password=your_password
```

3. **Run Application**
```bash
# Windows
gradlew.bat bootRun

# Linux/Mac
./gradlew bootRun
```

4. **Build JAR**
```bash
gradlew.bat clean build
java -jar build/libs/NewTracker-0.0.1-SNAPSHOT.jar
```

### Testing the API

```bash
# Get all task lists
curl http://localhost:8080/task-lists

# Create a task list
curl -X POST http://localhost:8080/task-lists \
  -H "Content-Type: application/json" \
  -d '{"title":"My Tasks","description":"Daily tasks"}'

# Health check (Spring Actuator)
curl http://localhost:8080/actuator/health
```

---

## ⚙️ Configuration

### application.properties

```properties
# Application Name
spring.application.name=NewTracker

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/tasktracker
spring.datasource.username=root
spring.datasource.password=*******

# JPA/Hibernate Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Redis Configuration (Prepared for caching)
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

### docker-compose.yml
- **MySQL Service**: Port 3307 (external) → 3306 (internal)
- **Application Service**: Port 8080
- **Persistent Volume**: Database data preserved across restarts

---

## 🎯 Key Implementation Details

### Mapper Pattern
**Purpose**: Converts between Entity objects (database) and DTO objects (API)

**Why?**
- Decouples API contract from database schema
- Allows different representations for internal/external data
- Prevents exposing internal entity relationships to API consumers

### Service Layer
**Purpose**: Business logic, validation, and orchestration

**Features**:
- Input validation (null checks, blank checks)
- Default value assignment (MEDIUM priority, OPEN status)
- Timestamp management (created, updated)
- Exception handling with proper HTTP status codes

### Repository Pattern
**Spring Data JPA** provides automatic implementations for database operations

### Global Exception Handler
Centralized error handling for consistent API responses across all endpoints.

---

## 📊 Database Schema

### Tables

#### task_list
| Column | Type | Constraints |
|--------|------|-------------|
| id | UUID | PRIMARY KEY |
| title | VARCHAR(255) | NOT NULL |
| description | TEXT | - |
| created | DATETIME | NOT NULL |
| updated | DATETIME | NOT NULL |

#### tasks
| Column | Type | Constraints |
|--------|------|-------------|
| id | UUID | PRIMARY KEY |
| title | VARCHAR(255) | NOT NULL |
| description | TEXT | - |
| due_date | DATETIME | NOT NULL |
| status | ENUM | NOT NULL |
| priority | ENUM | NOT NULL |
| tasklist_id | UUID | FOREIGN KEY |
| created | DATETIME | NOT NULL |
| updated | DATETIME | NOT NULL |

### Relationships
- **TaskList** ↔ **Task**: One-to-Many (Cascade DELETE)

## 📝 Development Notes

### Project Location
```
E:\SpringbootProject\application\NewTracker\
```

### Build Output
```
build/classes/java/main/com/tracker/newtracker/
```

### Common Issues

**Issue**: "Cannot resolve source sets"
**Solution**: Ensure JDK 21 is installed and JAVA_HOME is set correctly

**Issue**: Database connection error
**Solution**: Verify MySQL is running and credentials in application.properties are correct

**Issue**: Port 8080 already in use
**Solution**: Change port in application.properties: `server.port=8081`

---




