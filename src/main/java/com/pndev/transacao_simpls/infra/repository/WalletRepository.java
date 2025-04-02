package com.pndev.transacao_simpls.infra.repository;

import com.pndev.transacao_simpls.infra.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
