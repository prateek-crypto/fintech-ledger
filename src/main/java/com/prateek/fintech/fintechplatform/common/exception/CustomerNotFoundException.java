package com.prateek.fintech.fintechplatform.common.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
}

// Custom exceptions make your error handling explicit and maintainable.