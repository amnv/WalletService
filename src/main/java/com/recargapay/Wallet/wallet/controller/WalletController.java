package com.recargapay.Wallet.wallet.controller;

import com.recargapay.Wallet.wallet.dto.WalletRequest;
import com.recargapay.Wallet.wallet.dto.WalletResponse;
import com.recargapay.Wallet.wallet.entity.WalletEntity;
import com.recargapay.Wallet.wallet.mapper.WalletMapper;
import com.recargapay.Wallet.wallet.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;
    private final WalletMapper walletMapper;

    @PostMapping(value = "/v1/wallets", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WalletResponse create(@RequestBody @Valid WalletRequest walletRequest) {
        WalletEntity wallet = walletService.create(walletMapper.toWalletEntity(walletRequest));
        return walletMapper.toWalletResponse(wallet);
    }
}
