package com.prateek.fintech.fintechplatform.customer.entity;

public enum CustomerStatus {
    ACTIVE,
    BLOCKED,
    CLOSED
}

// We use an enum to prevent arbitrary values when a field is supposed to have a fixed set of valid constants/states.