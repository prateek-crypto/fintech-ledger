package com.prateek.fintech.fintechplatform.customer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "customers",
        indexes = {
                @Index(
                        name = "idx_customers_customer_number",
                        columnList = "customer_number"
                )
        }
)
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(
            name = "customer_number",
            nullable = false,
            unique = true,
            length = 20
    )
    private String customerNumber;

    @Column(
            name = "full_name",
            nullable = false,
            length = 100
    )
    private String fullName;

    @Column(
            nullable = false,
            unique = true,
            length = 150
    )
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CustomerStatus status;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    protected void onCreate() {

        OffsetDateTime now = OffsetDateTime.now();

        createdAt = now;
        updatedAt = now;

        if (status == null) {
            status = CustomerStatus.ACTIVE;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }
}

// @Entity
//Tells JPA:
//Treat Customer as a persistent entity.

// @Table
//@Table(name = "customers")
//Maps the Java class to:
//customers
//in PostgreSQL.

// @Id
//private UUID id;
//Defines the primary key.

//@GeneratedValue(strategy = GenerationType.UUID)
//Tells JPA to generate the UUID.
//You already used the same concept in Account.

// Column
//For example:
//@Column(nullable = false, unique = true)
//private String email;
//This represents database-level rules:
//email
// ├── NOT NULL
// └── UNIQUE
//This is important because business validation in Java is not a replacement for database integrity constraints.

// @PrePersist
//protected void onCreate()
//Runs before a new Customer is inserted.
//We're using it to initialize:
//createdAt
//updatedAt
//status

//@PreUpdate
//protected void onUpdate()
//Runs before an existing Customer is updated.
//We update:
//updatedAt

// Lombok
//@Getter
//@Setter
//generates getters and setters for us.
//You don't need to memorize Lombok syntax. Just understand what it does.