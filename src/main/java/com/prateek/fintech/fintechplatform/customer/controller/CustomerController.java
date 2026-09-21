package com.prateek.fintech.fintechplatform.customer.controller;

import com.prateek.fintech.fintechplatform.customer.dto.CreateCustomerRequest;
import com.prateek.fintech.fintechplatform.customer.dto.CustomerResponse;
import com.prateek.fintech.fintechplatform.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(
            @Valid @RequestBody CreateCustomerRequest request
    ) {
        return customerService.createCustomer(request);
    }
    // Concept: Path variable
// Why: the customer ID is part of the URL and must be passed to the service.

    @GetMapping("/{customerId}")
    public CustomerResponse getCustomer(
            @PathVariable UUID customerId
    ) {
        return customerService.getCustomer(customerId);
    }
}