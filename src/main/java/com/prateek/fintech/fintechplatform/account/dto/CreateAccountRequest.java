package com.prateek.fintech.fintechplatform.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * This Record acts as a Data Transfer Object (DTO) for validating
 * incoming client data when a new account is created.
 *
 * Using a Java 'record' makes this class immutable and automatically
 * generates boilerplate code like constructors, getters, equals(), and hashCode().
 */
public record CreateAccountRequest(

        // Ensures the account number is not empty/null and caps it at 20 characters.
        @NotBlank(message = "Account number is required")
        @Size(max = 20, message = "Account number must not exceed 20 characters")
        String accountNumber,

        // Ensures the currency is not empty/null and strictly enforces that only "INR" is accepted.
        @NotBlank(message = "Currency is required")
        @Pattern(
                regexp = "INR",
                message = "Only INR accounts are currently supported"
        )
        String currency
) {
}
// public record CreateAccountRequest(...) Java record is useful for simple immutable data carriers.
//
//Instead of writing:private String accountNumber;
//
//public String getAccountNumber() { ... }
//
//public void setAccountNumber(...) { ... }

// @NotBlank prevents null from being accepted @Size

//@Size(max = 20)
//
// SHOULD KNOW
//
//Ensures the account number doesn't exceed our database column's maximum length.
//
//This is an example of keeping API validation aligned with database constraints.

//@Pattern
//@Pattern(regexp = "INR")
// SHOULD KNOW
//Currently we're intentionally restricting the first implementation to INR.
//Later, when we introduce multi-currency support