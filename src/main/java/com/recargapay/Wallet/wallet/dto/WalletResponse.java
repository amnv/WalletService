package com.recargapay.Wallet.wallet.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.recargapay.Wallet.balance.dto.BalanceResponse;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletResponse {

    private String id;
    private String username;
    private String cpf;
    @JsonBackReference
    private BalanceResponse balanceResponse;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
