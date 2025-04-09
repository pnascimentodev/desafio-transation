package com.pndev.transacao_simpls.services;


import com.pndev.transacao_simpls.controller.TransactionDTO;
import com.pndev.transacao_simpls.infra.clients.ClientNotification;
import com.pndev.transacao_simpls.infra.repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final ClientNotification clientNotification;

    public void sendNotification() {
        clientNotification.sendNotification();
    }
}
