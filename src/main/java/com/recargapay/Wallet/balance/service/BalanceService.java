package com.recargapay.Wallet.balance.service;

import com.recargapay.Wallet.balance.model.BalanceEntity;
import com.recargapay.Wallet.balance.repository.BalanceRepository;
import com.recargapay.Wallet.statement.service.StatementService;
import com.recargapay.Wallet.wallet.model.WalletEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    public BalanceEntity getBalance(String walledId) throws ChangeSetPersister.NotFoundException {
        BalanceEntity balance = balanceRepository.findByWalletId(walledId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        statementService.addOperationBalance(balance);
        return balance;
    }
}
