package com.recargapay.Wallet.statement.model;

import com.recargapay.Wallet.balance.model.BalanceEntity;
import com.recargapay.Wallet.statement.enums.Operation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "statement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private BalanceEntity owner;

    // Case it is a transfer operation
    @ManyToOne
    @JoinColumn(name = "target_id")
    private BalanceEntity target;

    @Column(name = "operation_value", nullable = false)
    private Double operationValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Operation operation;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
