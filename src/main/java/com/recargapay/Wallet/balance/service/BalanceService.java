package com.recargapay.Wallet.balance.service;

import com.recargapay.Wallet.balance.model.BalanceEntity;
import com.recargapay.Wallet.balance.repository.BalanceRepository;
import com.recargapay.Wallet.wallet.model.WalletEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;

    public BalanceEntity create(WalletEntity wallet) {
        return balanceRepository.save(BalanceEntity.builder()
                .value(0.0)
                .wallet(wallet)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());
    }

    public BalanceEntity findByWalletId(String walledId) throws ChangeSetPersister.NotFoundException {
        return balanceRepository.findByWalletId(walledId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public List<BalanceEntity> list() {
        return balanceRepository.findAll();
    }
}
