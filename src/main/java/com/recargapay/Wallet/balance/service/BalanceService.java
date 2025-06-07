package com.recargapay.Wallet.balance.service;

import com.recargapay.Wallet.balance.model.BalanceEntity;
import com.recargapay.Wallet.balance.repository.BalanceRepository;
import com.recargapay.Wallet.statement.service.StatementService;
import com.recargapay.Wallet.wallet.model.WalletEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
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
        if (balanceOpt.isEmpty()) {
            log.error(String.format("The wallet id %s is not registered", walletId));
            return;
        }

        BalanceEntity balance = balanceOpt.get();
        balance.setValue(balance.getValue() + value);
        balance.setUpdatedAt(LocalDateTime.now());
        BalanceEntity balanceSaved = balanceRepository.save(balance);

        statementService.addOperationDeposit(balanceSaved, value);
    }

    @Transactional
    public void withdraw(String walletId, Double value) {
        BalanceEntity balance = getAndValidateBalanceEntity(walletId, value);
        if (balance == null) return;

        balance.setValue(balance.getValue() - value);
        balance.setUpdatedAt(LocalDateTime.now());
        BalanceEntity balanceSaved = balanceRepository.save(balance);

        statementService.addOperationWithdraw(balanceSaved, value);
    }

    @Transactional
    public void transfer(String ownerWalletId, String targetWalletID, Double value) {
        BalanceEntity balanceOwner = getAndValidateBalanceEntity(ownerWalletId, value);
        if (balanceOwner == null) return;

        Optional<BalanceEntity> balanceTargetOpt = balanceRepository.findByWalletId(targetWalletID);
        if (balanceTargetOpt.isEmpty()) {
            log.error("The wallet id {} is not registered", targetWalletID);
            return;
        }

        // Withdraw in owner
        balanceOwner.setValue(balanceOwner.getValue() - value);
        balanceOwner.setUpdatedAt(LocalDateTime.now());
        BalanceEntity balanceOwnerSaved = balanceRepository.save(balanceOwner);

        // deposit in target
        BalanceEntity balanceTarget = balanceTargetOpt.get();
        balanceTarget.setValue(balanceTarget.getValue() + value);
        balanceTarget.setUpdatedAt(LocalDateTime.now());
        BalanceEntity balanceTargetSaved = balanceRepository.save(balanceTarget);

        statementService.addOperationTransfer(balanceOwnerSaved, balanceTargetSaved, value);
    }

    private BalanceEntity getAndValidateBalanceEntity(String walletId, Double value) {
        Optional<BalanceEntity> balanceOpt = balanceRepository.findByWalletId(walletId);
        if (balanceOpt.isEmpty()) {
            log.error("The wallet id {} is not registered", walletId);
            return null;
        }

        BalanceEntity balance = balanceOpt.get();

        if (balance.getValue() - value < 0) {
            log.error("Your balance is insufficient to complete this operation.");
            return null;
        }

        return balance;
    }
}
