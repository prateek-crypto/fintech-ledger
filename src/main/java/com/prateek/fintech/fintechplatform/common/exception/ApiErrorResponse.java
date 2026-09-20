package com.prateek.fintech.fintechplatform.common.exception;

import java.time.OffsetDateTime;

public record ApiErrorResponse(
        OffsetDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {
}

// This is another DTO/record.
//We're defining a standard API error contract.
//Instead of every exception returning a different structure, clients receive a predictable format.