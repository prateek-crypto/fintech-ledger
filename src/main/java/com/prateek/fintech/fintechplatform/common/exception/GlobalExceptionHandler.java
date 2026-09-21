package com.prateek.fintech.fintechplatform.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;


import java.util.stream.Collectors;
import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public org.springframework.http.ResponseEntity<ApiErrorResponse> handleAccountAlreadyExists(
            AccountAlreadyExistsException exception,
            HttpServletRequest request
    ) {

        ApiErrorResponse response = new ApiErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "ACCOUNT_ALREADY_EXISTS",
                exception.getMessage(),
                request.getRequestURI()
        );

        return org.springframework.http.ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public org.springframework.http.ResponseEntity<ApiErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {

        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ApiErrorResponse response = new ApiErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_ERROR",
                message,
                request.getRequestURI()
        );

        return org.springframework.http.ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleCustomerAlreadyExists(
            CustomerAlreadyExistsException exception,
            HttpServletRequest request
    ) {

        ApiErrorResponse response = new ApiErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "CUSTOMER_ALREADY_EXISTS",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleCustomerNotFound(
            CustomerNotFoundException exception,
            HttpServletRequest request
    ) {
        ApiErrorResponse response = new ApiErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "CUSTOMER_NOT_FOUND",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}

// @RestControllerAdvice
//This tells Spring:
//"This class provides centralized exception handling for REST controllers."

// ExceptionHandler
//@ExceptionHandler(AccountAlreadyExistsException.class)
//This tells Spring:
//"When this particular exception occurs, execute this handler."

//Why HTTP 409?
//HttpStatus.CONFLICT
//means the request conflicts with the current state of the resource.

// MethodArgumentNotValidException
//This is the exception Spring raises when:
//@Valid
//finds that the request DTO violates its validation constraints.

// getFieldErrors()
//exception.getBindingResult()
//        .getFieldErrors()
//gets the individual validation failures.
// For:
//{
//  "accountNumber": "",
//  "currency": "USD"
//}
//we could have errors such as:
//accountNumber → Account number is required
//currency → Only INR accounts are currently supported

// stream()
//.stream()
//.map(...)
//.collect(...)
//This is Java Stream API.
//You should understand the flow, but don't waste time memorizing this exact chain.
//Conceptually:
//List of validation errors
//        ↓
//convert each error to a message
//        ↓
//combine messages
//        ↓
//one response message
//404 NOT_FOUND means the requested resource does not exist.