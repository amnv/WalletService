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
public class WithdrawHandler {

    private final BalanceService balanceService;

    @KafkaListener(topics = KafkaConstants.WITHDRAW_TOPIC,
            groupId = KafkaConstants.GROUP_ID,
            containerFactory = "fundKafkaListenerContainerFactory")
    public void listen(FundRequest fundRequest) {
        log.info("Received message at topic: {}", KafkaConstants.WITHDRAW_TOPIC);
        balanceService.withdraw(fundRequest.getOwnerWalletId(), fundRequest.getValue());
    }
}
