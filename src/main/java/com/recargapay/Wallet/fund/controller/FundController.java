package com.recargapay.Wallet.fund.controller;

import com.recargapay.Wallet.fund.dto.FundRequest;
import com.recargapay.Wallet.fund.service.FundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/funds")
@RequiredArgsConstructor
@Log4j2
public class FundController {

    private final FundService fundService;

    @PostMapping(value = "/deposits", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deposit(@Valid @RequestBody FundRequest fundRequest) {

        fundService.deposit(fundRequest);
    }


}
