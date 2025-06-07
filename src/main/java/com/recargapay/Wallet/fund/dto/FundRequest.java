package com.recargapay.Wallet.fund.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FundRequest {

    @NotEmpty
    @UUID
    private String ownerWalletId;

    @UUID
    private String targetWalletId;

    @NotNull
    private Double value;
}
