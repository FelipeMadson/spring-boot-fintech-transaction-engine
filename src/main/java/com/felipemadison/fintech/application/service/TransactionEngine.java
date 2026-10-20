package com.felipemadison.fintech.application.service;

import com.felipemadison.fintech.domain.model.TransactionRecord;
import com.felipemadison.fintech.domain.model.TransactionStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TransactionEngine {
    private final ConcurrentHashMap<String, TransactionRecord> idempotencyStore = new ConcurrentHashMap<>();

    public TransactionRecord process(
        String idempotencyKey,
        String sourceAccount,
        String destinationAccount,
        BigDecimal amountUsd
    ) {
        if (amountUsd.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transaction amount must be strictly positive");
        }

        return idempotencyStore.computeIfAbsent(idempotencyKey, key -> {
            String transactionId = "TXN-" + UUID.randomUUID();
            return new TransactionRecord(
                transactionId,
                key,
                sourceAccount,
                destinationAccount,
                amountUsd,
                TransactionStatus.CONFIRMED,
                Instant.now()
            );
        });
    }

    public int getProcessedCount() {
        return idempotencyStore.size();
    }
}
