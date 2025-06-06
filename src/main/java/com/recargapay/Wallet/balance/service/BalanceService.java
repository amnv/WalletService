package com.recargapay.Wallet.balance.service;

import com.recargapay.Wallet.balance.model.BalanceEntity;
import com.recargapay.Wallet.balance.repository.BalanceRepository;
import com.recargapay.Wallet.statement.service.StatementService;
import com.recargapay.Wallet.wallet.model.WalletEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final StatementService statementService;

    public BalanceEntity create(WalletEntity wallet) {
        return balanceRepository.save(BalanceEntity.builder()
                .value(0.0)
                .wallet(wallet)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());
    }

    @Transactional
    public BalanceEntity getBalance(String walletId) throws ChangeSetPersister.NotFoundException {
        BalanceEntity balance = balanceRepository.findByWalletId(walletId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        statementService.addOperationBalance(balance);
        return balance;
    }

    @Transactional
    public void deposit(String walletId, Double value) {
        Optional<BalanceEntity> balanceOpt = balanceRepository.findByWalletId(walletId);
        if (balanceOpt.isEmpty()) return;

        BalanceEntity balance = balanceOpt.get();
        balance.setValue(balance.getValue() + value);
        balance.setUpdatedAt(LocalDateTime.now());
        BalanceEntity balanceSaved = balanceRepository.save(balance);

        statementService.addOperationDeposit(balanceSaved);
    }
}
