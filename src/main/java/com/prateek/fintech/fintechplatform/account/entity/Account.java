package com.prateek.fintech.fintechplatform.account.entity;

import com.prateek.fintech.fintechplatform.customer.entity.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
// 🔥 MUST KNOW: Marks this class as a JPA entity.
// Hibernate maps this Java class to a database table.
@Table(
        name = "accounts",
        indexes = {
                @Index(
                        name = "idx_accounts_account_number",
                        columnList = "account_number"
                )
        }
)
// 🟡 SHOULD KNOW: Lombok generates getters and setters automatically.
@Getter
@Setter
public class Account {

    // 🔥 MUST KNOW: Primary key of the Account entity.
    @Id

    // 🔥 MUST KNOW: Hibernate generates the UUID automatically.
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // 🔥 MUST KNOW:
    // Maps accountNumber -> account_number.
    // NOT NULL + UNIQUE enforce database-level integrity.
    @Column(
            name = "account_number",
            nullable = false,
            unique = true,
            length = 20
    )
    private String accountNumber;

    // 🔥 MUST KNOW:
    // BigDecimal is used for monetary values because it provides
    // precise decimal arithmetic instead of floating-point arithmetic.
    //
    // precision = 19 -> total number of digits
    // scale = 2      -> digits after decimal point
    @Column(
            nullable = false,
            precision = 19,
            scale = 2
    )
    private BigDecimal balance;

    // 🟡 SHOULD KNOW:
    // ISO-style 3-character currency code such as INR, USD, EUR.
    @Column(
            nullable = false,
            length = 3
    )
    private String currency;

    // 🔥 MUST KNOW:
    // Stores the enum name (ACTIVE, BLOCKED, CLOSED)
    // rather than its numeric ordinal (0, 1, 2).
    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20
    )
    private AccountStatus status;

    // 🔥 MUST KNOW:
    // Enables optimistic locking.
    // Prevents concurrent updates from silently overwriting
    // another transaction's changes.
    @Version
    private Long version;

    // 🟡 SHOULD KNOW:
    // OffsetDateTime stores a timestamp together with its offset.
    @Column(
            name = "created_at",
            nullable = false
    )
    private OffsetDateTime createdAt;

    // 🟡 SHOULD KNOW:
    // Updated whenever the entity is modified.
    @Column(
            name = "updated_at",
            nullable = false
    )
    private OffsetDateTime updatedAt;

    // 🔥 MUST KNOW:
    // JPA lifecycle callback executed before INSERT.
    @PrePersist
    protected void onCreate() {

        OffsetDateTime now = OffsetDateTime.now();

        createdAt = now;
        updatedAt = now;

        if (balance == null) {
            balance = BigDecimal.ZERO;
        }

        if (currency == null) {
            currency = "INR";
        }

        if (status == null) {
            status = AccountStatus.ACTIVE;
        }
    }

    // 🔥 MUST KNOW:
    // JPA lifecycle callback executed before UPDATE.
    @PreUpdate
    protected void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }
    // Concept: JPA entity relationship
// Why: tells Hibernate that many Account records can belong to one Customer.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}

// @ManyToOne
//means:
//Many Account objects
//        ↓
//One Customer object
//This:
//@JoinColumn(name = "customer_id")
//tells Hibernate:
//The relationship is stored using the customer_id column in the accounts table.
//And this:
//fetch = FetchType.LAZY
//means:
//Don't automatically load the Customer whenever I load an Account.