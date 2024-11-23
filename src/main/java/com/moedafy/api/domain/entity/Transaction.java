package com.moedafy.api.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_favor", referencedColumnName = "id")
    private Favor favor;

    @ManyToOne
    @JoinColumn(name = "id_wallet_origin", referencedColumnName = "id")
    private Wallet walletOrigin;

    @ManyToOne
    @JoinColumn(name = "id_wallet_destinate", referencedColumnName = "id", nullable = true)
    private Wallet walletDestinate;

    private LocalDateTime createdAt;

    private Integer value;
}
