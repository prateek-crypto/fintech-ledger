# FinTech Core Banking Platform

A backend-focused FinTech Core Banking Platform being developed with Java and Spring Boot.

The project is being built incrementally, starting from the core banking data model and gradually moving toward transaction processing, ledger management, fraud detection, performance engineering, security, DevSecOps, and cloud deployment.

The current implementation covers the initial **Customer and Account foundation** of the platform.

---

## Technology Stack

### Backend
- Java 17
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Hibernate
- Jakarta Bean Validation
- Lombok

### Database
- PostgreSQL 16
- Flyway Database Migration

### Testing
- JUnit
- Spring Boot Test
- MockMvc
- Postman

### Development
- Maven
- Docker
- Git
- GitHub

---

# Day 1 — Project Foundation & Account Management

The first day was focused on setting up the application and building the initial Account domain.

The goal was to establish a clean foundation instead of immediately adding complex banking functionality.

## What was implemented

### Spring Boot Project Setup

Created the Spring Boot application using:

- Java 17
- Spring Boot 4.1.1
- Maven
- Spring Web MVC
- Spring Data JPA
- PostgreSQL Driver
- Flyway
- Validation
- Actuator
- Lombok
- Spring Boot DevTools

The application was connected to a dedicated PostgreSQL database named:

```text
fintech
