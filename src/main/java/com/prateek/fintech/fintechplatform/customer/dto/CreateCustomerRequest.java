package com.prateek.fintech.fintechplatform.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCustomerRequest(

        @NotBlank(message = "Customer number is required")
        @Size(
                max = 20,
                message = "Customer number must not exceed 20 characters"
        )
        String customerNumber,

        @NotBlank(message = "Full name is required")
        @Size(
                max = 100,
                message = "Full name must not exceed 100 characters"
        )
        String fullName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(
                max = 150,
                message = "Email must not exceed 150 characters"
        )
        String email
) {
}

// @NotBlank
//Prevents empty/blank values.
//@Email
//Validates email format.
//@Size
//Controls maximum input length.
//And:
//record
//is useful for DTOs because DTOs are primarily data carriers.