package com.recargapay.Wallet.wallet.service;

import com.recargapay.Wallet.balance.model.BalanceEntity;
import com.recargapay.Wallet.balance.service.BalanceService;
import com.recargapay.Wallet.statement.service.StatementService;
import com.recargapay.Wallet.wallet.model.WalletEntity;
import com.recargapay.Wallet.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final BalanceService balanceService;
    private final StatementService statementService;

    @Transactional
    public WalletEntity create(WalletEntity wallet) {
        wallet.setCreatedAt(LocalDateTime.now());
        wallet.setUpdatedAt(LocalDateTime.now());
        WalletEntity walletSaved = walletRepository.save(wallet);
        BalanceEntity balance = balanceService.create(walletSaved);
        walletSaved.setBalance(balance);

        statementService.addOperationCreate(balance);
        return walletSaved;
    }

}
