package com.recargapay.Wallet.balance.mapper;

import com.recargapay.Wallet.balance.dto.BalanceResponse;
import com.recargapay.Wallet.balance.model.BalanceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BalanceMapper {

   // @Mapping(target = "walletId", source = "balance.id")
    BalanceResponse toBalanceResponse(BalanceEntity balance);
}
