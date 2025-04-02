package com.pndev.transacao_simpls.infra.repository;

import com.pndev.transacao_simpls.infra.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
}
