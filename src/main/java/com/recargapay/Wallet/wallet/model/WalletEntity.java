package com.recargapay.Wallet.wallet.model;

import com.recargapay.Wallet.balance.model.BalanceEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "wallet")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE wallet SET deleted_at = now() WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 255)
    private String username;

    @Column(nullable = false, length = 14)
    private String cpf;

    @OneToOne(mappedBy = "wallet", orphanRemoval = true)
    private BalanceEntity balance;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
