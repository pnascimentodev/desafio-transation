package com.pndev.transacao_simpls.controller;


import com.pndev.transacao_simpls.services.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransactionController {

    private final TransactionsService transactionsService;

    @PostMapping
    public ResponseEntity<Void> transfer(@RequestBody TransactionDTO transactionDTO) {
        transactionsService.transferAmount(transactionDTO);
        return ResponseEntity.accepted().build();
    }
}
