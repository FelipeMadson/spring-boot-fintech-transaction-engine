package com.felipemadison.fintech;

import com.felipemadison.fintech.application.service.TransactionEngine;
import com.felipemadison.fintech.domain.model.TransactionRecord;
import com.felipemadison.fintech.domain.model.TransactionStatus;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionEngineTest {

    @Test
    void shouldProcessTransactionWithIdempotency() {
        TransactionEngine engine = new TransactionEngine();
        String key = "IDEMP-KEY-98765";

        TransactionRecord first = engine.process(key, "ACC-001", "ACC-002", new BigDecimal("5000.00"));
        assertNotNull(first);
        assertEquals(TransactionStatus.CONFIRMED, first.status());

        // Replay with identical idempotency key must return exact same record
        TransactionRecord replay = engine.process(key, "ACC-001", "ACC-002", new BigDecimal("5000.00"));
        assertEquals(first.transactionId(), replay.transactionId());
        assertEquals(1, engine.getProcessedCount());
    }

    @Test
    void shouldRejectNonPositiveAmount() {
        TransactionEngine engine = new TransactionEngine();
        assertThrows(IllegalArgumentException.class, () -> {
            engine.process("KEY-INVALID", "ACC-001", "ACC-002", new BigDecimal("-10.00"));
        });
    }
}
