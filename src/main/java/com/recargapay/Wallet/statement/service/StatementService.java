package com.recargapay.Wallet.statement.service;

import com.recargapay.Wallet.balance.model.BalanceEntity;
import com.recargapay.Wallet.statement.enums.Operation;
import com.recargapay.Wallet.statement.model.StatementEntity;
import com.recargapay.Wallet.statement.repository.StatementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class StatementService {

    private final StatementRepository statementRepository;

    public void addInfo(StatementEntity statement) {
        statementRepository.save(statement);
    }

    public void addOperation(BalanceEntity owner, BalanceEntity target, Double value, Operation operation) {
        this.addInfo(StatementEntity.builder()
                .owner(owner)
                .target(target)
                .operationValue(value)
                .operation(operation)
                .build());
    }

    public void addOperationCreate(BalanceEntity owner) {
        this.addOperation(owner, null, 0.0, Operation.CREATE_WALLET);
    }

    public void addOperationBalance(BalanceEntity owner) {
        this.addOperation(owner, null, owner.getValue(), Operation.BALANCE);
    }

    public void addOperationDeposit(BalanceEntity owner) {
        this.addOperation(owner, null, owner.getValue(), Operation.DEPOSIT);
    }

    public void addOperationWithdraw(BalanceEntity owner) {
        this.addOperation(owner, null, owner.getValue(), Operation.WITHDRAW);
    }

    public void addOperationTransfer(BalanceEntity owner, BalanceEntity target) {
        this.addOperation(owner, target, owner.getValue(), Operation.TRANSFER);
    }

    public Page<StatementEntity> detailBalance(String balanceId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return statementRepository.findAllByBalanceIdAndCreatedAtBetween(balanceId, startDate, endDate, pageable);
    }
}
