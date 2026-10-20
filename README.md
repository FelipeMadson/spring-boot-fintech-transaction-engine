# Spring Boot 3 Fintech Transaction Engine

[![CI](https://github.com/FelipeMadson/spring-boot-fintech-transaction-engine/actions/workflows/ci.yml/badge.svg)](https://github.com/FelipeMadson/spring-boot-fintech-transaction-engine/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Release](https://img.shields.io/badge/release-v1.0.0-green.svg)](https://github.com/FelipeMadson/spring-boot-fintech-transaction-engine/releases)

High-throughput enterprise financial transaction engine built with **Spring Boot 3.3** and **Java 21 Virtual Threads (Project Loom)**. Developed by [Felipe Madison](https://github.com/FelipeMadson).

## Architectural Topology

```text
┌────────────────────────────────────────────────────────────────────────┐
│                        HTTP & INGRESS GATEWAY                          │
│   • Virtual Thread per Request (Project Loom Lightweight Concurrency)  │
│   • RFC-7807 Problem Details                                           │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │
┌───────────────────────────────────▼────────────────────────────────────┐
│                        APPLICATION ENGINE                              │
│   • Concurrent Idempotency Key Gate (Deduplication Store)              │
│   • Transactional Outbox Pattern Dispatcher                           │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │
┌───────────────────────────────────▼────────────────────────────────────┐
│                        DOMAIN MODEL                                    │
│   • Immutable TransactionRecord (Java Records)                         │
│   • Strict BigDecimal Financial Precision                              │
└────────────────────────────────────────────────────────────────────────┘
```

## Key Highlights

- **Java 21 Virtual Threads**: Massive concurrent throughput without reactive framework complexity (`spring.threads.virtual.enabled: true`).
- **Strict Idempotency**: Deduplicates duplicate payments with concurrent key locks.
- **Financial Precision**: Enforces `BigDecimal` arithmetic for banking-grade ledger accuracy.

## Build and Test

```bash
mvn clean test
```

## Author

Authored and maintained by **[Felipe Madison](https://github.com/FelipeMadson)**.
