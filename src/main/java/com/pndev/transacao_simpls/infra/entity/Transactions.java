package com.pndev.transacao_simpls.infra.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "transactions")
@Table
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal amount;

    @JoinColumn(name = "receiver_id")
    @ManyToOne
    private User receiver;

    @JoinColumn(name = "payer_id")
    @ManyToOne
    private User payer;
    private LocalDateTime dateTimeTransaction;

    @PrePersist
    void prePersist() {
        dateTimeTransaction = LocalDateTime.now();
    }
}
