package com.prateek.fintech.fintechplatform.account.dto;

import com.prateek.fintech.fintechplatform.account.entity.AccountStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record AccountResponse(
        UUID id,
        String accountNumber,
        BigDecimal balance,
        String currency,
        AccountStatus status,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
//Why response DTO?
//
//The response is an API contract.
//
//We explicitly decide what information our API exposes.
//
//For example, we don't need to expose:
//
//version
//
//to the client in this first API version.
//
//The database's optimistic-locking implementation is an internal persistence concern.