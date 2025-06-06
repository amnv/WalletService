package com.recargapay.Wallet.fund.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FundRequest {

    @NotEmpty
    private String ownerWalletId;
    private String targetWalletId;

    @NotNull
    private Double value;
}
