package com.recargapay.Wallet.balance.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.recargapay.Wallet.wallet.dto.WalletResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalanceResponse {

    private String id;
    @JsonManagedReference
    private WalletResponse wallet;
    private Double value;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
