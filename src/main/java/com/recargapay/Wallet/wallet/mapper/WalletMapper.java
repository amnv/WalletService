package com.recargapay.Wallet.wallet.mapper;

import com.recargapay.Wallet.wallet.dto.WalletRequest;
import com.recargapay.Wallet.wallet.dto.WalletResponse;
import com.recargapay.Wallet.wallet.entity.WalletEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    WalletEntity toWalletEntity(WalletRequest walletRequest);

    WalletResponse toWalletResponse(WalletEntity wallet);
}
