package com.felipemadison.fintech.domain.model;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionRecord(
    String transactionId,
    String idempotencyKey,
    String sourceAccount,
    String destinationAccount,
    BigDecimal amountUsd,
    TransactionStatus status,
    Instant timestamp
) {}
