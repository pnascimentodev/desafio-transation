    package com.pndev.transacao_simpls.infra.entity;


    import jakarta.persistence.*;
    import lombok.*;

    import java.math.BigDecimal;
    import java.time.LocalDateTime;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity (name = "transactions")
    @Table
    @Builder
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


        @JoinColumn(name = "payee_id")
        @ManyToOne
        private User payee;

        private LocalDateTime dateTimeTransaction;

        @PrePersist
        void prePersist() {
            dateTimeTransaction = LocalDateTime.now();
        }
    }
