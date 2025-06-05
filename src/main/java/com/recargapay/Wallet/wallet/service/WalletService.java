package com.recargapay.Wallet.wallet.service;

import com.recargapay.Wallet.balance.model.BalanceEntity;
import com.recargapay.Wallet.balance.service.BalanceService;
import com.recargapay.Wallet.wallet.model.WalletEntity;
import com.recargapay.Wallet.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final BalanceService balanceService;

    public WalletEntity create(WalletEntity wallet) {
        wallet.setCreatedAt(LocalDateTime.now());
        wallet.setUpdatedAt(LocalDateTime.now());
        WalletEntity walletSaved = walletRepository.save(wallet);
        walletSaved.setBalance(balanceService.create(walletSaved));
        return walletSaved;
    }

}
