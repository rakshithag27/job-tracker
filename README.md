# Job Application Tracker API

A RESTful API built with Spring Boot that helps you track job applications, interviews, and outcomes. Built as a personal project to demonstrate backend development, JWT authentication, and containerization with Docker.

---

## Tech Stack

- **Java 17**
- **Spring Boot 3.2**
- **Spring Security + JWT** (JJWT 0.11.5)
- **Spring Data JPA + Hibernate**
- **MySQL 8.0**
- **Docker**
- **Docker Compose**
- **Maven**

---

## Features

- User signup and login with JWT authentication
- Passwords encrypted with BCrypt
- Create, read, update, and delete job applications
- Track application status: `APPLIED`, `ASSESSMENT`, `INTERVIEW`, `OFFER`, `REJECTED`, `GHOSTED`, `WITHDRAWN`
- Each user can only access and modify their own data
- Stateless REST API — no sessions

---

## Project Structure

```
src/main/java/com/rakshitha/jobtracker
├── controller        # API endpoints
├── service           # Business logic
├── repository        # Database access
├── model             # Entity classes
├── dto               # Request/Response objects
└── security          # JWT filter, config, user details
```

---

## API Endpoints

### Auth (Public)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/signup` | Register a new user |
| POST | `/api/auth/login` | Login and receive JWT token |

### Job Applications (Protected — requires JWT)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/jobs` | Add a new job application |
| GET | `/api/jobs` | Get all your job applications |
| PUT | `/api/jobs/{id}` | Update a job application |
| DELETE | `/api/jobs/{id}` | Delete a job application |

---

## Running Locally

### Prerequisites
- Java 17
- MySQL 8.0
- Maven

### Steps

1. Clone the repository
```bash
git clone https://github.com/rakshithag27/job-tracker.git
cd job-tracker
```

2. Create the database
```sql
CREATE DATABASE jobtracker;
```

3. Update `src/main/resources/application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobtracker
spring.datasource.username=root
spring.datasource.password=your_password
```

4. Run the app
```bash
mvn spring-boot:run
```

App runs on `http://localhost:8080`

---

## Running with Docker Compose (Recommended)

The easiest way to run the full stack — no local MySQL installation needed. Docker Compose spins up both the Spring Boot app and MySQL in separate containers and connects them automatically.

### Prerequisites
- Docker Desktop

### Steps

1. Build the jar
```bash
mvn clean package -DskipTests
```

2. Start everything
```bash
docker-compose up --build
```

That's it. Docker Compose will:
- Pull the MySQL 8.0 image
- Create the `jobtracker` database automatically
- Build the Spring Boot app image
- Wait for MySQL to be healthy before starting the app
- Connect both containers on a private network

App runs on `http://localhost:8080`  
MySQL is accessible on `localhost:3307` from your machine (e.g. MySQL Workbench)

3. To stop everything
```bash
docker-compose down
```

To stop and delete all data:
```bash
docker-compose down -v
```

---

## Running with Docker only

1. Build the jar
```bash
mvn clean package -DskipTests
```

2. Build the Docker image
```bash
docker build -t job-tracker .
```

3. Run the container
```bash
docker run -p 8080:8080 job-tracker
```

Note: this requires a MySQL instance running and accessible from the container.

---

## Authentication

All protected endpoints require a Bearer token in the Authorization header:

```
Authorization: Bearer <your_jwt_token>
```

Get the token from the `/api/auth/login` response and include it in every subsequent request.

---

## Sample Requests

**Signup**
```json
POST /api/auth/signup
{
    "email": "user@example.com",
    "password": "yourpassword",
    "fullName": "Your Name"
}
```

**Login**
```json
POST /api/auth/login
{
    "email": "user@example.com",
    "password": "yourpassword"
}
```

**Add a Job Application**
```json
POST /api/jobs
Authorization: Bearer <token>

{
    "companyName": "Google",
    "role": "Software Developer II",
    "jobUrl": "https://careers.google.com",
    "appliedDate": "2026-03-11",
    "status": "APPLIED",
    "notes": "Applied through referral"
}
```

**Update Application Status**
```json
PUT /api/jobs/1
Authorization: Bearer <token>

{
    "companyName": "Google",
    "role": "Software Developer II",
    "jobUrl": "https://careers.google.com",
    "appliedDate": "2026-03-11",
    "status": "INTERVIEW",
    "notes": "Got interview scheduled for next week"
}
```

---

## Upcoming

- [ ] AWS deployment (EC2 + RDS)
- [ ] Swagger UI for API documentation
