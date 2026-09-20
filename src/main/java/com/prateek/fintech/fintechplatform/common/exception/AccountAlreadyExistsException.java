package com.prateek.fintech.fintechplatform.common.exception;

public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException(String message) {
        super(message);
    }
}

//extends RuntimeException
//means this is an application-specific unchecked exception.
//We're creating our own meaningful exception instead of throwing a generic:
//IllegalArgumentException
//This lets the API distinguish:
//Account already exists
//from:
//Database unavailable
//or:
//Invalid request