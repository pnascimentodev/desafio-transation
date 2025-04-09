package com.pndev.transacao_simpls.controller;

import com.pndev.transacao_simpls.infra.entity.TypeUser;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal amount, Long payer, Long payee) {
}
