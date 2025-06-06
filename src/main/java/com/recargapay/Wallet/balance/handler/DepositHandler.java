package com.recargapay.Wallet.balance.handler;

import com.recargapay.Wallet.balance.service.BalanceService;
import com.recargapay.Wallet.fund.dto.FundRequest;
import com.recargapay.Wallet.util.KafkaConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class DepositHandler {

    private final BalanceService balanceService;

    @KafkaListener(topics = KafkaConstants.DEPOSIT_TOPIC,
            groupId = "wallet-group",
            containerFactory = "fundKafkaListenerContainerFactory")
    public void listen(FundRequest fundRequest) {
        log.info(String.format("mensgem recebida no topico: %s", KafkaConstants.DEPOSIT_TOPIC));
        balanceService.deposit(fundRequest.getOwnerWalletId(), fundRequest.getValue());
    }
}
