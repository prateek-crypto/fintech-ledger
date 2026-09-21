# FinTech Core Banking Platform

An enterprise-oriented Core Banking and Financial Transaction Platform built with Java and Spring Boot.

The project is being developed incrementally, with a focus on building the foundations of a real financial backend: customer and account management, transactional integrity, double-entry ledgering, fraud monitoring, performance optimization, observability, security, containerization, and eventually cloud deployment.

The goal is not just to build REST APIs, but to understand how backend systems used in financial applications are designed, tested, secured, monitored, and optimized.

---

## Current Progress

### Day 1 — Project Foundation & Account Management

The first day focused on getting the core backend infrastructure working and building the initial Account domain.

### What was completed

- Created the Spring Boot application using Java 17.
- Configured PostgreSQL as the primary relational database.
- Connected the application to a dedicated `fintech` PostgreSQL database.
- Integrated Flyway for database schema versioning.
- Configured Hibernate/JPA with `ddl-auto=validate` so that database schema changes remain controlled through Flyway.
- Created the initial `accounts` database table.
- Implemented the `Account` JPA entity.
- Added account status handling using an enum.
- Used `BigDecimal` for account balances instead of floating-point types because financial amounts require precise decimal representation.
- Added UUID-based account identifiers.
- Added timestamps for account creation and updates.
- Added optimistic locking groundwork using `@Version`.
- Created the Account repository using Spring Data JPA.
- Implemented request and response DTOs so the API layer is separated from the database entity.
- Added request validation.
- Implemented the account creation service.
- Implemented the REST endpoint for account creation.
- Added centralized exception handling.
- Added duplicate-account handling using both application-level validation and a database `UNIQUE` constraint.
- Tested the API manually using Postman.
- Verified that created accounts were persisted correctly in PostgreSQL.
- Added automated integration testing using Spring Boot and MockMvc.
- Fixed and documented the JVM/PostgreSQL timezone configuration issue.
- Connected the project to GitHub.

### Initial Account API

```text
POST /api/v1/accounts
