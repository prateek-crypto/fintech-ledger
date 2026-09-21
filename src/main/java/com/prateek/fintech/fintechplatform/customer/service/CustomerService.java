package com.prateek.fintech.fintechplatform.customer.service;

import com.prateek.fintech.fintechplatform.common.exception.CustomerAlreadyExistsException;
import com.prateek.fintech.fintechplatform.customer.dto.CreateCustomerRequest;
import com.prateek.fintech.fintechplatform.customer.dto.CustomerResponse;
import com.prateek.fintech.fintechplatform.customer.entity.Customer;
import com.prateek.fintech.fintechplatform.customer.entity.CustomerStatus;
import com.prateek.fintech.fintechplatform.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerResponse createCustomer(CreateCustomerRequest request) {

        if (customerRepository.existsByCustomerNumber(
                request.customerNumber())) {

            throw new CustomerAlreadyExistsException(
                    "Customer number already exists"
            );
        }

        if (customerRepository.existsByEmail(request.email())) {

            throw new CustomerAlreadyExistsException(
                    "Email already exists"
            );
        }

        Customer customer = new Customer();

        customer.setCustomerNumber(request.customerNumber());
        customer.setFullName(request.fullName());
        customer.setEmail(request.email());
        customer.setStatus(CustomerStatus.ACTIVE);

        Customer savedCustomer = customerRepository.save(customer);

        return new CustomerResponse(
                savedCustomer.getId(),
                savedCustomer.getCustomerNumber(),
                savedCustomer.getFullName(),
                savedCustomer.getEmail(),
                savedCustomer.getStatus(),
                savedCustomer.getCreatedAt(),
                savedCustomer.getUpdatedAt()
        );
    }
    // Concept: Optional + service-layer retrieval
// Why: findById() may not find a customer, so we explicitly handle the "not found" case.

    @Transactional(readOnly = true)
    public CustomerResponse getCustomer(UUID customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Customer not found")
                );

        return new CustomerResponse(
                customer.getId(),
                customer.getCustomerNumber(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getStatus(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }
}

// @Service
//Tells Spring:
//This class contains service/business logic and should be managed as a Spring bean.
//So Spring can inject it into our controller later.

// @Transactional
//public CustomerResponse createCustomer(...)
//What does it mean?
//The database operations inside this method execute within a transaction.
//Conceptually:
//BEGIN
//   ↓
//check customer
//   ↓
//create customer
//   ↓
//save
//   ↓
//COMMIT
//If an appropriate runtime exception causes the transaction to fail:
//ROLLBACK
//Why are we introducing it now?
//Because later our financial operations will contain multiple database changes that must succeed or fail together.
//For example:
//Transfer
// ├── debit Account A
// ├── credit Account B
// └── create transaction record
//We will use transaction boundaries heavily there.
//Don't think:
//"@Transactional means database operation."
//Think:
//@Transactional defines a transaction boundary around a unit of work.


// if (customerRepository.existsByCustomerNumber(
//        request.customerNumber())) {
//We're checking whether the requested customer number already exists.
//If yes:
//throw new IllegalArgumentException(
//        "Customer number already exists"
//);
//Same idea for email.


// Create the Entity
//Customer customer = new Customer();
//Then populate only the fields that the client is allowed to provide:
//customer.setCustomerNumber(request.customerNumber());
//customer.setFullName(request.fullName());
//customer.setEmail(request.email());
//And we control the status ourselves:
//customer.setStatus(CustomerStatus.ACTIVE);
//The client doesn't decide whether a newly created customer is blocked.

// Save
//Customer savedCustomer = customerRepository.save(customer);
//Spring Data/JPA persists the entity.