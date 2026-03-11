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
git clone https://github.com/rakshithagreddy/job-tracker.git
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

## Running with Docker

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

---

## Authentication

All protected endpoints require a Bearer token in the Authorization header:

```
Authorization: Bearer <your_jwt_token>
```

Get the token from the `/api/auth/login` response and include it in every subsequent request.

---

## Sample Request

**Signup**
```json
POST /api/auth/signup
{
    "email": "user@example.com",
    "password": "yourpassword",
    "fullName": "Your Name"
}
```

**Add a Job Application**
```json
POST /api/jobs
{
    "companyName": "Google",
    "role": "Software Developer II",
    "jobUrl": "https://careers.google.com",
    "appliedDate": "2026-03-11",
    "status": "APPLIED",
    "notes": "Applied through referral"
}
```

---

## Upcoming

- [ ] AWS deployment (EC2 + RDS)
- [ ] Docker Compose for local multi-container setup
- [ ] Swagger UI for API documentation
