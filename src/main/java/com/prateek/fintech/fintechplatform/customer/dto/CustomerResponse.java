package com.prateek.fintech.fintechplatform.customer.dto;

import com.prateek.fintech.fintechplatform.customer.entity.CustomerStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String customerNumber,
        String fullName,
        String email,
        CustomerStatus status,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}

// 