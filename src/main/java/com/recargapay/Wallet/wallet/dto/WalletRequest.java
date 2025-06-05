package com.recargapay.Wallet.wallet.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletRequest {

    @NotEmpty
    @Size(max = 255)
    private String username;

    @NotEmpty
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$", message = "Enter a valid CPF.")
    private String cpf;
}
