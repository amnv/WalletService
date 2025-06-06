package com.recargapay.Wallet.balance.model;

import com.recargapay.Wallet.statement.model.StatementEntity;
import com.recargapay.Wallet.wallet.model.WalletEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "balance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE balance SET deleted_at = now() WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
public class BalanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", nullable = false, unique = true)
    private WalletEntity wallet;

    @Column(name = "balance_value", nullable = false)
    private Double value;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<StatementEntity> statementsAsOwner;

    @OneToMany(mappedBy = "target", cascade = CascadeType.ALL)
    private List<StatementEntity> statementsAsTarget;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}

