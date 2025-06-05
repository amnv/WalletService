package com.recargapay.Wallet.wallet.service;

import com.recargapay.Wallet.wallet.entity.WalletEntity;
import com.recargapay.Wallet.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletEntity create(WalletEntity wallet) {
        wallet.setCreatedAt(LocalDateTime.now());
        wallet.setUpdatedAt(LocalDateTime.now());
        return walletRepository.save(wallet);
    }
}
