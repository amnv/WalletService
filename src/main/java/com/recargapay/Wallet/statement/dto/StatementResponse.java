package com.recargapay.Wallet.statement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.recargapay.Wallet.balance.dto.BalanceResponse;
import com.recargapay.Wallet.statement.enums.Operation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatementResponse {

    private BalanceResponse owner;
    private BalanceResponse target;
    private Double operationValue;
    private Operation operation;
    private LocalDateTime createdAt;
}
