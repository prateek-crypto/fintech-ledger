# FinTech Core Banking Platform

An enterprise-oriented **Core Banking & Transaction Processing Platform** built with Java and Spring Boot.

This project is being developed as a long-term engineering project rather than a simple CRUD application. The goal is to gradually build a production-style financial backend covering account management, transactions, double-entry ledgering, fraud detection, asynchronous processing, security, observability, performance engineering, DevSecOps, containerization, Kubernetes, cloud deployment, caching, messaging, and AI-assisted capabilities.

---

## Why I'm Building This

Most backend projects stop after:

> Controller → Service → Repository → Database

That is useful for learning, but real financial systems have much harder problems:

- How do we prevent duplicate transactions?
- How do we maintain financial consistency?
- How do we handle concurrent updates?
- How do we guarantee that money is not created or lost?
- How do we recover from failures?
- How do we process work asynchronously?
- How do we detect suspicious transactions?
- How does the system behave under heavy traffic?
- How do we measure performance before and after optimization?
- How do we secure the software supply chain?
- How do we deploy and monitor the system in containers and Kubernetes?

This project is my attempt to work through those problems incrementally and understand the engineering decisions behind them.

---

# Current Status

### Day 1 — Account Foundation

**Status: Complete**

The first milestone establishes the foundation of the platform:

- Spring Boot application setup
- PostgreSQL integration
- Flyway database migrations
- Account domain model
- Account REST API
- Request validation
- Global exception handling
- Database constraints
- Optimistic locking groundwork
- Integration testing
- Git/GitHub workflow
- Reproducible JVM timezone configuration

Automated tests:

```text
5 tests
5 passed
0 failures
0 errors
