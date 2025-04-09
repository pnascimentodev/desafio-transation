package com.pndev.transacao_simpls.services;


import com.pndev.transacao_simpls.controller.TransactionDTO;
import com.pndev.transacao_simpls.exceptions.BadRequestExeption;
import com.pndev.transacao_simpls.infra.clients.ClientNotification;
import com.pndev.transacao_simpls.infra.entity.Transactions;
import com.pndev.transacao_simpls.infra.entity.TypeUser;
import com.pndev.transacao_simpls.infra.entity.User;
import com.pndev.transacao_simpls.infra.entity.Wallet;
import com.pndev.transacao_simpls.infra.repository.TransactionsRepository;
import feign.Client;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionsService {

    private final UserService userService;
    private final AuthorizationService authorizationService;
    private final WalletService walletService;
    private final TransactionsRepository transactionsRepository;
    private final NotificationService notificationService;

    @Transactional
    public void transferAmount(TransactionDTO transactionDTO) {
        User payer = userService.findUser(transactionDTO.payer());
        User payee = userService.findUser(transactionDTO.payee());

        validateUser(payer);
        checkBalance(payer, transactionDTO.amount());
        transferValidate();

        payer.getWallet().setBalance(payer.getWallet().getBalance().subtract(transactionDTO.amount()));
        updateBalance(payer.getWallet());

        payee.getWallet().setBalance(payee.getWallet().getBalance().add(transactionDTO.amount()));
        updateBalance(payee.getWallet());

        Transactions transactions = Transactions.builder()
                .amount(transactionDTO.amount())
                .payer(payer)
                .payee(payee)
                .build();
        transactionsRepository.save(transactions);
        sendNotification();
    }

    private void validateUser(User user) {
        try {
            if (user.getTypeUser().equals(TypeUser.storekeeper)){
                throw new IllegalArgumentException("Transação não autorizada para o tipo de usuário");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void checkBalance(User user, BigDecimal balance) {
        try {
            if(user.getWallet().getBalance().compareTo(balance) < 0){
                throw new IllegalArgumentException("Transação não autorizada, saldo insuficiente");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void transferValidate() {
        try {
           if (!authorizationService.transferValidate()){
               throw new IllegalArgumentException("Transação não autorizada");
           }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void updateBalance(Wallet wallet) {
        walletService.save(wallet);
    }

    private void sendNotification(){
        try {
            notificationService.sendNotification();
        } catch (HttpClientErrorException e) {
            throw new BadRequestExeption("Erro ao enviar notificação");
            }
    }

}
