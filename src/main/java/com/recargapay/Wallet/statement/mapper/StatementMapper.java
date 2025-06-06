package com.recargapay.Wallet.statement.mapper;

import com.recargapay.Wallet.statement.dto.StatementResponse;
import com.recargapay.Wallet.statement.model.StatementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatementMapper {

    StatementResponse toStatementResponse(StatementEntity statement);
}
