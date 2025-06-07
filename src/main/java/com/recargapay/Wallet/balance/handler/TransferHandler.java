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
public class TransferHandler {
    private final BalanceService balanceService;

    @KafkaListener(topics = KafkaConstants.TRANSFER_TOPIC,
            groupId = "wallet-group",
            containerFactory = "fundKafkaListenerContainerFactory")
    public void listen(FundRequest fundRequest) {
        log.info("Received message at topic: {}", KafkaConstants.TRANSFER_TOPIC);
        balanceService.transfer(fundRequest.getOwnerWalletId(), fundRequest.getTargetWalletId(), fundRequest.getValue());
    }
}
