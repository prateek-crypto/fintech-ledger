package com.prateek.fintech.fintechplatform.account.controller;

import com.prateek.fintech.fintechplatform.account.dto.AccountResponse;
import com.prateek.fintech.fintechplatform.account.dto.CreateAccountRequest;
import com.prateek.fintech.fintechplatform.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createAccount(
            @Valid @RequestBody CreateAccountRequest request
    ) {
        return accountService.createAccount(request);
    }
}
// @RestController
//
//
//Tells Spring that this class handles REST HTTP requests and that returned objects should be written as the
// HTTP response body, typically as JSON.

// @RequestMapping
//@RequestMapping("/api/v1/accounts")
//Defines the base URL.
//Our endpoint becomes:
//POST /api/v1/accounts
//Why /v1?
//This establishes an API version.
//Later, if we make breaking API changes, we can introduce:
/// api/v2/accounts
//without necessarily breaking existing clients.

//PostMapping
//Maps the method to HTTP POST.
// POST is appropriate because we're creating a new resource.

//@RequestBody

//@RequestBody CreateAccountRequest request
//Tells Spring:
        //"Take the JSON request body and convert it into a CreateAccountRequest object."

//For example:
//{
//  "accountNumber": "ACC100001",
//  "currency": "INR"
//}
//becomes:
//CreateAccountRequest
//accountNumber = ACC100001
//currency = INR

//@Valid
//Activates the Bean Validation annotations we put on the DTO:
//@NotBlank
//@Size
//@Pattern
//So invalid requests can be rejected before they reach our business logic.

//HttpStatus.CREATED
//@ResponseStatus(HttpStatus.CREATED)
//returns:
//HTTP 201 Created
//instead of the default 200 OK.
