package com.prateek.fintech.fintechplatform.customer.repository;

import com.prateek.fintech.fintechplatform.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository
        extends JpaRepository<Customer, UUID> {

    boolean existsByCustomerNumber(String customerNumber);

    boolean existsByEmail(String email);
}

// 