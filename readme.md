# 📝 Spring Boot Todo API

A RESTful Todo Management API built using **Spring Boot**. This project was developed step by step to learn backend development, REST APIs, database integration, API documentation, authentication, and security using industry-standard practices.

---

# 🚀 Tech Stack

- Java 21
- Spring Boot 3.5.3
- Spring Data JPA
- PostgreSQL
- Spring Security
- JWT Authentication
- Swagger / OpenAPI
- Maven
- Lombok

---

# ✨ Features

- Create Todo
- Get Todo by ID
- Get All Todos
- Update Todo
- Delete Todo
- Pagination
- Request Validation
- Logging using SLF4J
- Swagger API Documentation
- Spring Security Configuration
- User Registration
- User Login
- JWT Token Generation
- JWT Token Validation

---

# 📂 Project Structure

```
src
 ├── controller
 │      ├── TodoController
 │      └── AuthController
 │
 ├── service
 │      ├── TodoService
 │      └── UserService
 │
 ├── repository
 │      ├── TodoRepository
 │      └── UserRepository
 │
 ├── models
 │      ├── Todo
 │      └── User
 │
 ├── util
 │      └── JwtUtil
 │
 └── config
        └── SecurityConfig
```

---

# ⚙️ How to Run

## Clone Repository

```bash
git clone https://github.com/diviya28/SpringBoot-Todo.git
```

## Open Project

```
Import as Maven Project
```

## Configure PostgreSQL

Create Database

```sql
CREATE DATABASE tododb;
```

Update

```
application.properties
```

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tododb
spring.datasource.username=postgres
spring.datasource.password=your_password
```

Run

```
TodoApplication.java
```

---

# 📖 API Documentation

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

# 📅 Development Journey

## Day 1

### Initial Project Setup

- Created Spring Boot Project
- Configured Maven
- Added Spring Web
- Added Spring Data JPA
- Configured PostgreSQL
- Created Todo Entity
- Created Todo Repository
- Created Todo Service
- Created Todo Controller

---

## Day 2

### REST APIs

Implemented

- Create Todo
- Get Todo
- Get All Todos
- Update Todo
- Delete Todo

---

## Day 3

### Refactoring

- Renamed project package

```
com.diviya.todo
```

---

## Day 4

### Logging & REST Improvements

Added

- SLF4J Logging
- ResponseEntity
- Better HTTP Status Codes
- RESTful API Responses

---

## Day 5

### Swagger

Integrated

- OpenAPI
- Swagger UI
- API Documentation
- API Responses

---

## Day 6

### Spring Security

Added

- Spring Security Dependency
- SecurityFilterChain
- Basic Authentication
- CSRF Configuration
- Swagger Access Configuration

---

## Day 7

### Database Refactoring

- Removed Description field from Todo Entity

---

## Day 8

### User Module

Created

- User Entity
- User Repository
- User Service
- Auth Controller

---

## Day 9

### JWT Utility

Implemented

- JWT Token Generation
- JWT Token Validation
- Secret Key Configuration

---

## Day 10

### Authentication

Implemented

- User Registration
- User Login
- Password Verification
- JWT Token Response

---

# 📚 Concepts Learned

## Spring Boot

- Controllers
- Services
- Repository Layer
- Dependency Injection
- Bean Lifecycle

## Spring Data JPA

- Entity
- Repository
- CRUD Operations
- Pagination

## REST APIs

- GET
- POST
- PUT
- DELETE

## Validation

- @Valid
- @Email
- @NotBlank

## Logging

- SLF4J
- INFO
- WARN

## API Documentation

- Swagger
- OpenAPI
- ApiResponse
- ApiResponses

## Spring Security

- SecurityFilterChain
- PasswordEncoder
- BCrypt
- CSRF
- Authentication

## JWT

- Token Generation
- Token Validation
- Subject
- Expiration
- Secret Key

---

# 🚀 Future Improvements

- JWT Authentication Filter
- Role-Based Authorization
- Refresh Tokens
- Global Exception Handling
- DTO Pattern
- ModelMapper
- Unit Testing
- Integration Testing
- Docker
- Deployment
- CI/CD

---

# 👩‍💻 Author

**Diviya Velu**

GitHub:
https://github.com/diviya28
