package com.pndev.transacao_simpls.services;


import com.pndev.transacao_simpls.infra.entity.Wallet;
import com.pndev.transacao_simpls.infra.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public void save(Wallet wallet) {
        walletRepository.save(wallet);
    }
}
