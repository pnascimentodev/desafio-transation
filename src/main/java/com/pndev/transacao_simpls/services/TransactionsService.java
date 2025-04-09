package com.pndev.transacao_simpls.services;


import com.pndev.transacao_simpls.controller.TransactionDTO;
import com.pndev.transacao_simpls.infra.entity.TypeUser;
import com.pndev.transacao_simpls.infra.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionsService {

    private final UserService userService;
    private final AuthorizationService authorizationService;


    @Transactional
    public void transferAmount(TransactionDTO transactionDTO) {
        User payer = userService.findUser(transactionDTO.payer());
        User payee = userService.findUser(transactionDTO.payee());

        validateUser(payer);
        checkBalance(payer, transactionDTO.amount());
        transferValidate();
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

}
