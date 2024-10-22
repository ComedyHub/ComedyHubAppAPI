# ComedyHub API

## Project Overview

ComedyHub API is a Spring Boot application that serves as the backend for a comedy-focused social platform. It provides user management functionality and is designed to be extended with additional features related to comedy content sharing and interaction.

## Features

- User registration and management
- RESTful API endpoints for user operations
- Swagger UI for API documentation and testing
- PostgreSQL database integration
- Spring Security configuration (currently set to allow all requests)

## Technology Stack

- Java 23
- Spring Boot 3.3.4
- Spring Data JPA
- Spring Security
- PostgreSQL
- Swagger (SpringDoc OpenAPI)
- Maven
- Thymeleaf (for server-side rendering)

## Project Structure

- `com.comedyhub.prot.controller`: Contains API controllers
- `com.comedyhub.prot.service`: Business logic layer
- `com.comedyhub.prot.repository`: Data access layer
- `com.comedyhub.prot.model`: Entity classes
- `com.comedyhub.prot.dto`: Data Transfer Objects
- `com.comedyhub.prot.config`: Configuration classes

## Configuration Files

- `application.properties`: Main configuration file
- `pom.xml`: Maven project configuration

The main application properties can be found in:

## Getting Started

### Prerequisites

- JDK 23
- Maven
- PostgreSQL

### Database Setup

1. Install PostgreSQL if you haven't already.
2. Create a new database named `comedyhub`.
3. Update the database configuration in `application.properties` if necessary:

### Running the Application

1. Clone the repository:
   ```
   git clone [repository-url]
   cd comedyhub-api
   ```

2. Build the project:
   ```
   mvn clean install
   ```

3. Run the application:
   ```
   mvn spring-boot:run
   ```

The application will start on `http://localhost:9091`.

## API Documentation

Swagger UI is integrated for API documentation and testing. Access it at:
java:prot/src/main/java/com/comedyhub/prot/config/SwaggerConfig.java

## API Endpoints

### User Management

- `POST /api/users`: Create a new user
- `GET /api/users`: Get all users
- `GET /api/users/{id}`: Get a user by ID

The implementation of these endpoints can be found in:

## Security

The current security configuration allows all requests without authentication. This is suitable for development but should be updated for production use. The security configuration can be found in:
java:prot/src/main/java/com/comedyhub/prot/config/SecurityConfig.java
