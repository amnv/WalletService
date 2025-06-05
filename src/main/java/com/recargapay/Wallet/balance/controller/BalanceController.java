package com.recargapay.Wallet.balance.controller;

import com.recargapay.Wallet.balance.dto.BalanceResponse;
import com.recargapay.Wallet.balance.mapper.BalanceMapper;
import com.recargapay.Wallet.balance.model.BalanceEntity;
import com.recargapay.Wallet.balance.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/balances")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;
    private final BalanceMapper balanceMapper;

    @GetMapping("/{walletId}")
    public BalanceResponse findByWalletId(@PathVariable String walletId) throws ChangeSetPersister.NotFoundException {
        return balanceMapper.toBalanceResponse(balanceService.findByWalletId(walletId));
    }

    @GetMapping
    public List<BalanceEntity> find() {
        return balanceService.list();
    }
}
