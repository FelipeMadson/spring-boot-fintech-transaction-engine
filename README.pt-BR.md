# Spring Boot 3 Fintech Transaction Engine

[![CI](https://github.com/FelipeMadson/spring-boot-fintech-transaction-engine/actions/workflows/ci.yml/badge.svg)](https://github.com/FelipeMadson/spring-boot-fintech-transaction-engine/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Release](https://img.shields.io/badge/release-v1.0.0-green.svg)](https://github.com/FelipeMadson/spring-boot-fintech-transaction-engine/releases)

Motor de processamento de transações financeiras de alta vazão desenvolvido com **Spring Boot 3.3** e **Java 21 Virtual Threads (Project Loom)**. Desenvolvido por [Felipe Madison](https://github.com/FelipeMadson).

## Topologia Arquitetural

```text
┌────────────────────────────────────────────────────────────────────────┐
│                        INGRESSO & API REST                             │
│   • Virtual Thread por Requisição (Concorrência Leve com Loom)         │
│   • Respostas Estruturadas RFC-7807                                    │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │
┌───────────────────────────────────▼────────────────────────────────────┐
│                        MOTOR DE TRANSAÇÃO                              │
│   • Deduplicação Concorrente por Chave de Idempotência                 │
│   • Padrão Transactional Outbox                                       │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │
┌───────────────────────────────────▼────────────────────────────────────┐
│                        MODELO DE DOMÍNIO                               │
│   • TransactionRecord Imutável (Java Records)                          │
│   • Precisão Monetária com BigDecimal                                  │
└────────────────────────────────────────────────────────────────────────┘
```

## Destaques de Engenharia

- **Virtual Threads no Java 21**: Altíssima taxa de requisições concorrentes com modelo imperativo simples.
- **Idempotência Rigorosa**: Protege contra pagamentos duplicados e retransmissões de rede.
- **Precisão Bancária**: Uso estrito de `BigDecimal` para evitar erros de ponto flutuante financeiro.

## Compilação e Testes

```bash
mvn clean test
```

## Autor

Desenvolvido por **[Felipe Madison](https://github.com/FelipeMadson)**.
