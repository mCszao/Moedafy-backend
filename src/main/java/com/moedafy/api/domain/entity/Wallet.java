package com.moedafy.api.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@NoArgsConstructor
public class Wallet {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private Integer balance = 0;

    // Transações onde a conta é a origem
    @OneToMany(mappedBy = "walletOrigin", cascade = CascadeType.ALL)
    private List<Transaction> transactionOrigin;

    // Transações onde a conta é o destino
    @OneToMany(mappedBy = "walletDestinate", cascade = CascadeType.ALL)
    private List<Transaction> transactionDestinate;

    // Método para consolidar uma lista geral
    @Transient
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        if (transactionOrigin != null) {
            transactions.addAll(transactionOrigin);
        }
        if (transactionDestinate != null) {
            transactions.addAll(transactionDestinate);
        }
        return transactions;
    }
}
