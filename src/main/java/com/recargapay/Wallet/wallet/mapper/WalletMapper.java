package com.recargapay.Wallet.wallet.mapper;

import com.recargapay.Wallet.balance.mapper.BalanceMapper;
import com.recargapay.Wallet.wallet.dto.WalletRequest;
import com.recargapay.Wallet.wallet.dto.WalletResponse;
import com.recargapay.Wallet.wallet.model.WalletEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", imports = BalanceMapper.class)
public interface WalletMapper {

    WalletEntity toWalletEntity(WalletRequest walletRequest);

    WalletResponse toWalletResponse(WalletEntity wallet);
}
