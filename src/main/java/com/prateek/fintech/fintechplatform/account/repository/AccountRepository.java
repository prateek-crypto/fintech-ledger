package com.prateek.fintech.fintechplatform.account.repository;

import com.prateek.fintech.fintechplatform.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * JpaRepository provides sophisticated CRUD (Create, Read, Update, Delete)
 * functionality and pagination capabilities for the Account entity.
 *
 * By extending JpaRepository<Account, UUID>, Spring Data JPA automatically
 * generates the underlying SQL queries and database connection logic at runtime.
 * You don't need to write any implementation code or SQL for standard database operations.
 */
public interface AccountRepository extends JpaRepository<Account, UUID> {
    // Account: Specifies the target database entity/table this repository manages.
    // UUID: Specifies the data type used for the primary key (@Id) of the Account entity.
    boolean existsByAccountNumber(String accountNumber);
    List<Account> findByCustomerId(UUID customerId);
}

// MUST KNOW
//List<Account>
//means multiple accounts can belong to one customer.
//🟡 SHOULD KNOW
//findByCustomerId
//is a Spring Data derived query method. Spring interprets the method name and generates the query.
//⚪ SYNTAX/UTILITY
//You don't need to memorize every possible derived-query name. Understand the pattern:
//findBy + EntityProperty
